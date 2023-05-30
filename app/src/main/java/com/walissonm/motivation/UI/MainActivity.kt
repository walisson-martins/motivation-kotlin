package com.walissonm.motivation.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.walissonm.motivation.infra.MotivationConstants
import com.walissonm.motivation.R
import com.walissonm.motivation.data.Mock
import com.walissonm.motivation.infra.SecurityPreferences
import com.walissonm.motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.image_inclusive)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageEmotions.setOnClickListener(this)
        binding.imageInclusive.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view?.id in listOf(
                R.id.image_emotions,
                R.id.image_inclusive,
                R.id.image_sunny
            )
        ) {
            handleFilter(view?.id)
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, ${name}!"
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrases(categoryId, Locale.getDefault().language)
    }

    private fun handleFilter(id: Int?) {
        binding.imageInclusive.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageEmotions.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_inclusive -> {
                binding.imageInclusive.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_emotions -> {
                binding.imageEmotions.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }
}