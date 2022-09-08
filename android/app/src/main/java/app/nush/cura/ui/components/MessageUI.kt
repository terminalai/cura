package app.nush.cura.ui.components

import app.nush.cura.model.chat.Message
import app.nush.cura.model.firebase.FirebaseUtil
import app.nush.cura.model.util.toEpoch
import com.stfalcon.chatkit.commons.models.IMessage
import java.util.*

class MessageUI(val msg: Message): IMessage {
    override fun getId() = msg.msgId
    override fun getText() = msg.msg
    override fun getUser() = FirebaseUtil.chats[msg.receiverId]!!.second
    override fun getCreatedAt() = msg.sent
}