package app.nush.cura.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.nush.cura.R
import app.nush.cura.databinding.FragmentRegistrationPaneOneBinding

class RegistrationPaneOneFragment : AuthFragment() {
    private var _binding: FragmentRegistrationPaneOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegistrationPaneOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.previous.setOnClickListener(NavigateOnClick(R.id.action_registrationPaneOneFragment_to_SecondFragment))
        binding.next.setOnClickListener(
            NavigateOnClick(
                R.id.action_registrationPaneOneFragment_to_registrationPaneTwoFragment,
                Bundle().apply {
                    putString("username", binding.username.text.toString())
                    putString("password", binding.password.text.toString())
                }
            )
        )
    }
}