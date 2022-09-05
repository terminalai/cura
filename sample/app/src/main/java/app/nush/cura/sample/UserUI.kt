package app.nush.cura.sample

import com.stfalcon.chatkit.commons.models.IUser

class UserUI(val user: User): IUser {
    override fun getId() = user.userId
    override fun getName() = user.userId
    override fun getAvatar() = user.profile
}