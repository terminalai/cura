package app.nush.chatdemo

import android.app.Application


class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Chat SDK intialization goes here
        ChatSDKFirebase.quickStart();
    }
}