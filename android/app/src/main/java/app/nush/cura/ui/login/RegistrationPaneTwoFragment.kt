package app.nush.cura.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import app.nush.cura.databinding.FragmentRegistrationPaneTwoBinding

class RegistrationPaneTwoFragment : Fragment() {
    private var _binding: FragmentRegistrationPaneTwoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationPaneTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), androidx.navigation.ui.R.layout.support_simple_spinner_dropdown_item, items)
        binding.list.setAdapter(adapter)
    }
}