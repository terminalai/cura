package app.nush.cura.ui.components

import app.nush.cura.model.chat.User
import com.stfalcon.chatkit.commons.models.IUser

class UserUI(val user: User): IUser {
    override fun getId() = user.userId
    override fun getName() = user.username
    override fun getAvatar() = ""
}