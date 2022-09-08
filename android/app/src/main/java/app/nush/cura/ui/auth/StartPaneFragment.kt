package app.nush.cura.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.nush.cura.R
import app.nush.cura.databinding.FragmentStartBinding


class StartPaneFragment : AuthFragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener(NavigateOnClick(R.id.action_FirstFragment_to_SecondFragment))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}