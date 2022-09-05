package app.nush.cura.sample

object UserDatabase {
    val sender = User("0001", "Marty McFly")
    val receiver = User("0002", "Emmett Brown")
    val userFactory = mapOf<User, UserUI>(
        sender to UserUI(sender),
        receiver to UserUI(receiver)
    )
}