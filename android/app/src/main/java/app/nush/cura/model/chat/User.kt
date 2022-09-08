package app.nush.cura.model.chat

data class User(
    val userId: String,
    val username: String,
    val password: String,
    val interests: List<String>,
    val chats: List<String>
)