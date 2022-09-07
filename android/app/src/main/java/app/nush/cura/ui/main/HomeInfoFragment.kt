package app.nush.cura.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.nush.cura.MainActivity
import app.nush.cura.R


class HomeInfoFragment: Fragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_info, container, false)

        val title = requireArguments().get("page_title").toString()
        val info = requireArguments().get("page_info").toString()
        val img = requireArguments().get("page_img")
        var webURL = requireArguments().get("page_link").toString()

        (requireActivity() as MainActivity).supportActionBar?.title = title

        view.findViewById<TextView>(R.id.home_info_title).text = title
        view.findViewById<TextView>(R.id.home_info_info).text = info
        view.findViewById<ImageView>(R.id.home_info_image).setImageResource(img as Int)

        view.findViewById<ImageView>(R.id.imageView2).setOnClickListener { v ->

            if (!webURL.startsWith("http://") && !webURL.startsWith("https://"))
                webURL = "http://$webURL"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webURL))
            startActivity(intent)
        }

        return view
    }
}