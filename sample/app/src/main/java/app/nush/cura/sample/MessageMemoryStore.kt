package app.nush.cura.sample

class MessageMemoryStore() {
    companion object {

        val instance = MessageMemoryStore()
    }

    val messages: HashMap<String, ArrayList<Message>> = hashMapOf()

    fun addMessage(receiverId: String, message: Message) {
        val msgs = messages.getOrDefault(receiverId, arrayListOf())
        messages[receiverId] = msgs
        msgs.forEach {
            if(it.msgId == message.msgId) return@addMessage
        }
        msgs.add(message)
    }
}