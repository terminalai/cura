package app.nush.cura.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import app.nush.cura.R
import app.nush.cura.databinding.FragmentRegistrationPaneTwoBinding
import app.nush.cura.model.chat.User
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class RegistrationPaneTwoFragment : AuthFragment() {
    private var _binding: FragmentRegistrationPaneTwoBinding? = null
    private lateinit var user: User
    private var interests = arrayListOf<String>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationPaneTwoBinding.inflate(inflater, container, false)
        binding.addInterest.setOnClickListener {
            val keyword: String = binding.menu.editText?.text.toString()
            if (keyword in interests) return@setOnClickListener
            try {
                val layoutInflater = LayoutInflater.from(this.context)
                val newChip = (layoutInflater.inflate(R.layout.layout_chip_entry, binding.chipGroup, false) as Chip).apply {
                    text = keyword
                    isCloseIconVisible = true
                    elevation = 15F
                    setOnCloseIconClickListener {
                        val chip = it as Chip
                        (chip.parent as ChipGroup).removeView(chip)
                    }
                }
                binding.chipGroup.addView(newChip)
                interests.add(keyword)
            } catch (e: Exception) {
                Toast.makeText(this.context, "Error: " + e.message, Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = listOf("Sailing", "Knitting", "Hiking", "Biking", "Swimming", "Crafts", "Sleeping")
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)
        binding.list.setAdapter(adapter)
        binding.previous.setOnClickListener(NavigateOnClick(R.id.action_registrationPaneTwoFragment_to_registrationPaneOneFragment))
        binding.next.setOnClickListener(NavigateOnClick(R.id.action_registrationPaneTwoFragment_to_SecondFragment))
    }
}