package com.furkan.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.furkan.flagquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = "Your Score is " + intent.getStringExtra("result")
        binding.tvPoint.text = result
        val name = intent.getStringExtra("name")
        binding.tvResultName.text = name

        binding.bntStartAgain.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
        binding.btnFinish.setOnClickListener {
            finish()
        }
    }
}