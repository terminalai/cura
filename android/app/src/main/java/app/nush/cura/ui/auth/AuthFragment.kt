package app.nush.cura.ui.auth

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

open class AuthFragment: Fragment() {
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    inner class NavigateOnClick(@IdRes val resId: Int): View.OnClickListener {
        override fun onClick(p0: View?) {
            findNavController().navigate(resId)
        }
    }
}