package app.nush.cura.model.chat

import com.stfalcon.chatkit.commons.models.IUser


class Author(id : String, name : String, avatar : String) : IUser {
    /*...*/
    override fun getId(): String {
        return id
    }

    override fun getName(): String {
        return name
    }

    override fun getAvatar(): String {
        return avatar
    }
}