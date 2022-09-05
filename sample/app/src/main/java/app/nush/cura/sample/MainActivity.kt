package app.nush.cura.sample

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import app.nush.cura.sample.UserDatabase.receiver
import app.nush.cura.sample.UserDatabase.sender
import app.nush.cura.sample.databinding.ActivityMainBinding
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null;

    lateinit var adapter: MessagesListAdapter<MessageUI>


    val binding: ActivityMainBinding
    get() = _binding!!

    private var msgIdCounter = 0
    private var yourMSGCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        adapter = MessagesListAdapter(sender.userId, CuraImageLoader())
        binding.messagesList.setAdapter(adapter)

        binding.input.setInputListener {
            val message = Message(
                String.format("%08d", ++msgIdCounter),
                it.toString(),
                sender,
                Date()

            )
            MessageMemoryStore.instance.addMessage(receiver.userId, message)
            MessageMemoryStore.instance.messages[receiver.userId]?.joinToString(", ")
                ?.let { it1 -> Log.d("messaging", it1) }
            reload()
            ++yourMSGCounter

            val mess = if(yourMSGCounter == 1) "I'm doing well, u?" else """Hello. This is the Cura Bot. This conversation is getting quite intense. If you ever feel like you need to speak with a professional, please call the hotlines page on the homepage, or call on of these numbers:
Samaritans of Singapore: 1800-221-4444
Institute of Mental Health: 6389-2222
Singapore Association for Mental Health: 1800-283-7019"""

            Thread {
                SystemClock.sleep(800)
                runOnUiThread {
                    MessageMemoryStore.instance.addMessage(
                        receiver.userId,
                        Message(
                            String.format("%08d", ++msgIdCounter),
                            mess,
                            receiver,
                            Date()
                        )
                    )
                    reload()
                }
            }.start()

//            runBlocking {
//                flow<Int> {
//                    reload()
//                    emit(0)
//                    delay(5000L)
//                    emit(1)
//
//                    MessageMemoryStore.instance.addMessage(
//                        receiver.userId,
//                        Message(
//                            String.format("%08d", ++msgIdCounter),
//                            "Having spent so long as a worker, my life has become but an unseemly system of what it used to be. I feel so tired all the time, so fizzled out.",
//                            receiver,
//                            Date()
//                        )
//                    )
//                    emit(2)
//                    reload()
//                    emit(3)
//                }.collect { test ->
//                    Log.d("messaging", test.toString())
//                }

                true
            }
        reload()
        }





    inner class CuraImageLoader: ImageLoader {
        override fun loadImage(imageView: ImageView?, url: String?, payload: Any?) {
        }

    }

    fun reload() {
        adapter.clear()
        MessageMemoryStore.instance.messages[receiver.userId]?.forEach {
            adapter.addToStart(MessageUI(it), true);
        }
    }

}