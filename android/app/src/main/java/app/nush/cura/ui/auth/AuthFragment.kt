package app.nush.cura.ui.auth

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class AuthFragment: Fragment() {
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }
}