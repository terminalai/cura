package app.nush.cura.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.nush.cura.R


class ChatViewAdapter() : RecyclerView.Adapter<ChatViewAdapter.ViewHolder>() {

    private val titles = listOf("FloatingBanana", "SoaringWatermelon")
    private val details = arrayOf("Last seen 5/9/2022", "Last seen 4/9/2022")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item_title.text = titles[position]
        holder.item_detail.text = details[position]
    }

    override fun getItemCount(): Int = titles.size

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val item_title: TextView = itemView.findViewById(R.id.item_title)
        val item_detail: TextView = itemView.findViewById(R.id.item_detail)
    }

}