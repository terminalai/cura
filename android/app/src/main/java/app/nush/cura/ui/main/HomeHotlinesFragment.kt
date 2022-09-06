package app.nush.cura.ui.main

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.nush.cura.R

class HomeHotlinesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_hotlines, container, false)

        val feiyue = view.findViewById<TextView>(R.id.home_hotline_website_feiyue)
        val silver = view.findViewById<TextView>(R.id.home_hotline_website_silver)
        val touchline = view.findViewById<TextView>(R.id.home_hotline_website_touchline)
        val helpbot = view.findViewById<TextView>(R.id.home_hotline_website_helpbot)
        val msf = view.findViewById<TextView>(R.id.home_hotline_website_msf)

        val lst = arrayListOf<TextView>(feiyue,silver,touchline,helpbot,msf)

        for (v: TextView in lst) {
            v.movementMethod = LinkMovementMethod.getInstance();
            v.setLinkTextColor(Color.BLUE)
        }



        return view
    }


}