package app.nush.cura.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.nush.cura.R
import app.nush.cura.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false);
        val homeFragment = HomeFragment()
        val chatFragment = ChatFragment()
        val settingsFragment = SettingsFragment()

        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(homeFragment)
                R.id.chat->setCurrentFragment(chatFragment)
                R.id.settings->setCurrentFragment(settingsFragment)

            }
            true
        }

        return binding.root;
    }

    private fun setCurrentFragment(fragment:Fragment) =
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}