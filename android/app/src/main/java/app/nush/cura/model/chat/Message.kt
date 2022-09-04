package app.nush.cura.model.chat

import java.time.LocalDateTime
import java.util.*

data class Message(
    val msgId: String, val msg: String, val sent: LocalDateTime,
    val senderId: String, val receiverId: String,
    val reply: Boolean = true, val replymsgId: String? = null
)