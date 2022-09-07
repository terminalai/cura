package app.nush.cura.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.nush.cura.R
import app.nush.cura.databinding.FragmentRegistrationPaneTwoBinding
import app.nush.cura.model.chat.User
import app.nush.cura.ui.auth.AuthFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationPaneTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationPaneTwoFragment : AuthFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRegistrationPaneTwoBinding? = null
    private lateinit var user: User;
    private var interests = arrayListOf<String>();



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationPaneTwoBinding.inflate(inflater, container, false)

        binding.addInterest.setOnClickListener { addNewChip()}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  user = User("123","Mario","password", arrayListOf<String>(), map); // todo pass in actjual data
        val items = listOf("Sailing", "Knitting", "Hiking", "Biking", "Swimming", "Crafts", "Sleeping")
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)
        binding.list.setAdapter(adapter)
        binding.next.setOnClickListener{

            findNavController().navigate(R.id.action_registrationPaneTwoFragment_to_SecondFragment)
        }
    }

    private fun addNewChip() {
        val keyword: String = binding.menu.editText?.text.toString()

        if (keyword in interests) {
            return
        }


        try {
            val inflater = LayoutInflater.from(this.context)

            // Create a Chip from Layout.
            val newChip =
                inflater.inflate(R.layout.layout_chip_entry, binding.chipGroup, false) as Chip
            newChip.text = keyword

            //
            // Other methods:
            //
            newChip.isCloseIconVisible = true;
            // newChip.setCloseIconResource(R.drawable.your_icon);
            // newChip.setChipIconResource(R.drawable.your_icon);
            // newChip.setChipBackgroundColorResource(R.color.red);
            // newChip.setTextAppearanceResource(R.style.ChipTextStyle);
            newChip.elevation = 15F;

            binding.chipGroup.addView(newChip)
            interests.add(keyword)

            // Set Listener for the Chip:
            newChip.setOnCloseIconClickListener { v -> handleChipCloseIconClicked(v as Chip) }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this.context, "Error: " + e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun handleChipCloseIconClicked(chip: Chip) {
        val parent = chip.parent as ChipGroup
        parent.removeView(chip)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationPaneTwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationPaneTwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}