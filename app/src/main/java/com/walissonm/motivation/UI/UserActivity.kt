package com.walissonm.motivation.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.walissonm.motivation.infra.MotivationConstants
import com.walissonm.motivation.R
import com.walissonm.motivation.infra.SecurityPreferences
import com.walissonm.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

        verifyUserName()
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        if (name != "") {
            //navegação de Activity(
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()

        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            //navegação de Activity(
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}