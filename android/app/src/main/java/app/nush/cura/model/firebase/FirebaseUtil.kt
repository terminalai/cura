package app.nush.cura.model.firebase

import android.util.Log
import android.widget.Toast
import app.nush.cura.model.chat.Message
import app.nush.cura.model.chat.User
import app.nush.cura.model.util.AES
import app.nush.cura.ui.components.UserUI
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import java.util.*
import kotlin.collections.ArrayList

object FirebaseUtil {
    private var FIRESTORE: FirebaseFirestore? = null
    var user: User? = null
    
    private const val KEY = "CURASplashAwardsMindfulHacks"
    private const val DELIMITER = "|~~!0!~~!0!~~!0!~~|"

    private val firestore: FirebaseFirestore
        get() {
            if (FIRESTORE == null) FIRESTORE = Firebase.firestore
            return FIRESTORE!!
        }

    private val aes = AES()

    public val chatMessages: HashMap<String, List<Message>> = hashMapOf()

    public val chats: HashMap<String, Pair<User, UserUI>> = hashMapOf()

    fun getLastMessage(receiverId: String): Pair<Message?, Int> {
        val chatId = generateChatId(user!!.userId, receiverId)
        var message: Message? = null;
        var unread: Int = 0
        chatCollection().document(chatId).get().addOnSuccessListener {
            it?.data?.let { data ->
                val lastMessage = data["lastMessage"] as String
                val lastSentEpochs = data["lastSent"] as Long
                val lastSentBy = data["lastSentBy"] as String
                unread = (data["msgCount"] as Int) - chatMessages.getOrDefault(receiverId, listOf()).size
                val lastReceivedBy = if(lastSentBy == receiverId) user!!.userId else receiverId

                val lastSent = Date(lastSentEpochs)
                val msgId = "${lastSentEpochs/1000}"
                message = Message(
                    msgId, lastMessage, lastSent, lastSentBy, lastReceivedBy
                )
            }
        }
        return message to unread
    }

    private fun loadMessages(receiverId: String): List<Message> {
        val chatId = generateChatId(user!!.userId, receiverId)
        var messages: List<Message> = listOf()
        chatCollection().document(chatId).collection("messages").get().addOnSuccessListener { result ->
            messages = result.filterNotNull().sortedBy { it["msgIdx"] as Int }.map {
                val data = it.data
                Message(
                    it["msgId"].toString(), it["msg"].toString(),
                    Date(it["createdAt"] as Long), it["senderId"].toString(), it["receiverId"].toString()
                )
            }
        }
        return messages
    }

    fun loadMessagesIn(receiverId: String) {
        chatMessages[receiverId] = loadMessages(receiverId)
    }
    
    fun sendMessage(receiverId: String, msg: String) {
        val chatId = generateChatId(user!!.userId, receiverId)
        val createdAt = System.currentTimeMillis()
        val msgId = "${createdAt / 1000}"
        val msgIdx = getLastMessage(receiverId).second + chatMessages.getOrDefault(receiverId, listOf()).size + 1

        val data = hashMapOf<String, Any?>(
            "msg" to msg, "msgId" to msgId, "msgIdx" to msgIdx,
            "receiverId" to receiverId, "senderId" to user!!.userId,
            "createdAt" to createdAt
        )

        chatCollection().document(chatId).collection("messages").document(msgId).set(data)

        val lastMessageData = hashMapOf<String, Any?>(
            "chatId" to chatId, "lastMessage" to msg,
            "lastSent" to createdAt, "lastSentBy" to user!!.userId,
            "msgCount" to msgId+1
        )

        chatCollection().document(chatId).set(lastMessageData)

        loadMessagesIn(receiverId)
    }

    fun userCollection() = firestore.collection("users")
    fun messageCollection() = firestore.collection("messages")
    fun chatCollection() = firestore.collection("chats")

    fun newUser(userData: Map<String, Any?>) = User(
        userData["id"] as String, userData["username"] as String, userData["password"] as String,
        userData["interests"] as List<String>, userData["chats"] as List<String>
    )

    fun register(username: String, password: String, interests: List<String>): Boolean {
        val id = generateId(username, password)

        val data = mutableMapOf(
            "userId" to id, "username" to username,
            "password" to password, "interests" to interests,
            "chats" to ArrayList<String>()
        )

        userCollection().document().get().addOnSuccessListener {
            // Already have an account!
            data["chats"] = it.data!!.getOrDefault("chats", listOf<String>()) as List<String>
        }

        var success = false
        userCollection().document(id).set(data).addOnSuccessListener {
            Log.d("AUTH", "Register Successful")
            success = true
        }.addOnFailureListener {
            Log.d("AUTH", "Register Unsuccessful")
        }

        user = newUser(data)
        return success
    }

    fun login(username: String, password: String): Boolean {
        val id = generateId(username, password)
        var success = false
        userCollection().document(id).get().addOnSuccessListener {
            Log.d("AUTH", "Logging In")
            it?.data?.let {data -> user = newUser(data) }
            success = true
        }.addOnFailureListener {
            Log.d("AUTH", "What just happened")
        }
        return success
    }

    private fun generateId(username: String, password: String) = "$username$DELIMITER$password"
    // encrypt(     )

    private fun generateChatId(senderId: String, receiverId: String) =
        if(senderId < receiverId) "$senderId$DELIMITER$receiverId"
        else "$receiverId$DELIMITER$senderId"


    private fun encrypt(string: String): String {
        var encryptedCode = aes.encrypt(string, KEY)
        if (encryptedCode == null) encryptedCode = string
        encryptedCode.replace(Regex("[/\\\\]+"), "")
        return encryptedCode
    }
}