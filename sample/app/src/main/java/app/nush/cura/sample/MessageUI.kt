package app.nush.cura.sample

import com.stfalcon.chatkit.commons.models.IMessage

class MessageUI(val message: Message): IMessage {
    override fun getId() = message.msgId
    override fun getText() = message.msg
    override fun getUser() = UserDatabase.userFactory[message.sender]
    override fun getCreatedAt() = message.created

}