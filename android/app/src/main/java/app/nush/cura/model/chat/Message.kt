package app.nush.cura.model.chat

import java.time.LocalDateTime
import java.util.*

data class Message(
    val msgId: String, val msg: String,
    val sent: Date,
    val senderId: String, val receiverId: String
)