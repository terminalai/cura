package app.nush.cura.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.nush.cura.R


class ChatViewAdapter() : RecyclerView.Adapter<ChatViewAdapter.ViewHolder>() {

    private val titles = listOf("The Frontliner", "Nurse")
    private val lastMessages = arrayOf("Good morning, how was the night shift?", "Hello! Heard you are a nurse too?")
    private val lastSeen = arrayOf("Today", "5/9")
    private val unreadMessageCount = arrayOf("5","1")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.chat_title.text = titles[position]
        holder.chat_last_message.text = lastMessages[position]
        holder.chat_unread_message_count.text = unreadMessageCount[position]
        holder.chat_last_seen.text = lastSeen[position]
    }

    override fun getItemCount(): Int = titles.size

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val chat_title: TextView = itemView.findViewById(R.id.chat_title)
        val chat_last_message: TextView = itemView.findViewById(R.id.chat_last_message)
        val chat_unread_message_count : TextView = itemView.findViewById(R.id.chat_unread_message_count)
        val chat_last_seen: TextView = itemView.findViewById(R.id.chat_last_seen)
    }

}