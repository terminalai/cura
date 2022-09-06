package app.nush.cura.ui.main

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import app.nush.cura.R

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val images = listOf(R.drawable.phone, R.drawable.cbt, R.drawable.img)
    private val titles = listOf("Hotlines", "Cognitive Behavioural Therapy", "Effective Communication")
    private val details = arrayOf("If you need help, Click for more helpful hotlines", "CBT has been shown to be extremely beneficial to anyone struggling with stress", "Learn about explaining your needs and getting what you want")
    private val info = arrayOf(R.string.hotlines, R.string.cbt, R.string.effective_communication)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_card_layout, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
        holder.item_title.text = titles[position]
        holder.item_detail.text = details[position]

        holder.itemView.setOnClickListener {view ->

if (position == 0) {
    Navigation.findNavController(view).navigate(R.id.homeFragment_to_homeHotlinesFragment)
} else {
    Navigation.findNavController(view).navigate(R.id.homeFragment_to_homeInfoFragment, Bundle().apply { putString("page_title",
        titles[position]
    ); putString("page_info", view.context.resources.getString(info[position]));
        putInt("page_img", images[position])
    })
}
}


    }

    override fun getItemCount(): Int = titles.size

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView3)
        val item_title: TextView = itemView.findViewById(R.id.item_title)
        val item_detail: TextView = itemView.findViewById(R.id.item_detail)

    }

}