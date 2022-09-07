package app.nush.cura.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.nush.cura.R
import app.nush.cura.databinding.FragmentHealthBinding
import kotlin.math.roundToInt


class HealthFragment : Fragment() {

    // TODO GET VALUE OF NEGATIVITY OR SOMETHING (from 0-100)
    val mental_wellness = 100

    val health_messages = arrayOf("Your sunflower is at full health! :) \n\nRemember: To be healthy as a whole, mental wellness plays a role!", //8
        "Your sunflower is very happy! Keep it up! \n\nMental health is not a destination, but the process. It's about how you drive, not where you're going!", // 7
        "Your sunflower is happy! Keep it up! \n\n Be kind to yourself!", // 6
        "Your sunflower could be better! It needs some care :-) \n\nBe kind to yourself!", // 5
        "Alert: Your sunflower needs care! \n\nIt's okay to take as much time as you need and to prioritise your mental health!", // 4
        "Alert: Your sunflower is wilting! \n\nYou don't need to face this alone. Reach out to your friends and family for help, or call a hotline in the Hotlines page!" , // 3
        "Alert: Your sunflower is severely dehydrated! \n\nSelf care is how you take your power back. Take care of yourself! \n\nYou don't need to face this alone. Reach out to your friends and family for help, or call a hotline in the Hotlines page!", // 2
        "Alert: Your sunflower is dying! \n\nThere is hope, even when your brain tells you there isn't! \n\nYou don't need to face this alone. Reach out to your friends and family for help, or call a hotline in the Hotlines page!", // 1
    ).reversedArray()

    var health_img = arrayOf(R.drawable.sunflower_1,
        R.drawable.sunflower_2,
        R.drawable.sunflower_3,
        R.drawable.sunflower_4,
        R.drawable.sunflower_5,
        R.drawable.sunflower_6,
        R.drawable.sunflower_7,
        R.drawable.sunflower_8,
    )

    private var _binding: FragmentHealthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHealthBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var mentalRating = (mental_wellness / 12.5).roundToInt()
        if (mentalRating < 1) {
            mentalRating = 1
        }

        binding.sunflowerImg.setImageResource(health_img[mentalRating-1])
        binding.sunflowerHealthPercentage.text = mental_wellness.toString() + "%"
        binding.sunflowerHealthText.text = health_messages[mentalRating-1]

        if (mentalRating >= 6) {
            binding.sunflowerHealthPercentage.setTextColor(Color.parseColor("#2AAA8A"))
            binding.textView5.setTextColor(Color.parseColor("#2AAA8A"))
            binding.sunflowerHealthText.setTextColor(Color.parseColor("#2AAA8A"))
        } else if (mentalRating >= 4) {
            binding.sunflowerHealthPercentage.setTextColor(Color.parseColor("#FFBF00"))
            binding.textView5.setTextColor(Color.parseColor("#FFBF00"))
            binding.sunflowerHealthText.setTextColor(Color.parseColor("#FFBF00"))
        } else {
            binding.sunflowerHealthPercentage.setTextColor(Color.parseColor("#C41E3A"))
            binding.textView5.setTextColor(Color.parseColor("#C41E3A"))
            binding.sunflowerHealthText.setTextColor(Color.parseColor("#C41E3A"))
        }

    }
}