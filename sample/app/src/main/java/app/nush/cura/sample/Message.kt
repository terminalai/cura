package app.nush.cura.sample

import java.util.*

data class Message(val msgId: String, val msg: String, val sender: User, val created: Date) {
}