package app.nush.cura.model.firebase

import android.util.Log
import app.nush.cura.model.chat.User
import app.nush.cura.model.util.AES
import app.nush.cura.ui.components.UserUI
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

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

    public val chats: Map<String, Pair<User, UserUI>> = mapOf()
    
    fun sendMessage(receiverId: String, msg: String) {
        user?.let {
            val msgId = generateMsgId(it, receiverId);
            val senderData = mapOf(
                "userId" to it.userId, "username" to it.username,
                "password" to it.password, "interests" to it.interests,
                "chats" to it.chats.also { chats ->
                    chats[receiverId]?.get("messages")?.add(msgId)
                }
            )

            userCollection().document(it.userId).set(senderData)
            userCollection().document(receiverId).get().addOnSuccessListener { resp ->
                resp?.data?.let { data ->
                    (data["chats"] as Map<String, Map<String, ArrayList<String>>>)[it.userId]?.get("messages")?.add(msgId)
                    userCollection().document(receiverId).set(data)
                }
            }

            val messageData = mapOf(
                "msgId" to msgId,
                "msg" to msg,
                "senderId" to it.userId,
                "receiverId" to receiverId,
                "sent" to Timestamp.now()
            )

            messageCollection().document(msgId).set(messageData)
        }

    }

    fun userCollection() = firestore.collection("users")
    fun messageCollection() = firestore.collection("messages")
    fun chatCollection() = firestore.collection("chats")

    fun newUser(userData: Map<String, Any?>) = User(
        userData["id"] as String, userData["username"] as String, userData["password"] as String,
        userData["interests"] as List<String>, userData["chats"] as Map<String, Map<String, ArrayList<String>>>
    )

    fun register(username: String, password: String, interests: List<String>) {
        val id = encrypt("$username~~!!~~!!~~$password")

        val data = mapOf(
            "userId" to id, "username" to username,
            "password" to password, "interests" to interests,
            "chats" to HashMap<String, HashMap<String, ArrayList<String>>>()
        )

        userCollection().document(id).get().addOnSuccessListener {
            // This means that this guy already has an account
            Log.d("AUTH", "Logging In Instead.")
        }.addOnFailureListener {
            // This means they're actually logging in
            Log.d("AUTH", "Registering!")
        }

        user = newUser(data)
        userCollection().document(id).set(data)
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

    private fun generateMsgId(sender: User, receiverId: String) = "${sender.userId}$DELIMITER${sender.chats.getOrDefault(receiverId, hashMapOf()).getOrDefault("messages", listOf<String>()).size+1}$DELIMITER$receiverId"


    private fun encrypt(string: String): String {
        var encryptedCode = aes.encrypt(string, KEY)
        if (encryptedCode == null) encryptedCode = string
        encryptedCode.replace(Regex("[/\\\\]+"), "")
        return encryptedCode
    }
}