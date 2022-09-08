package app.nush.cura.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.nush.cura.R
import app.nush.cura.databinding.FragmentHomeBinding
import app.nush.cura.model.firebase.FirebaseUtil

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.helloMessage.text = "Hello ${FirebaseUtil.user!!.username}"

        val recycler = binding.recycler


        if (recycler is RecyclerView) {
            with(recycler) {
                layoutManager = LinearLayoutManager(context)
                adapter = HomeAdapter()
            }
        }
        return binding.root
    }

}