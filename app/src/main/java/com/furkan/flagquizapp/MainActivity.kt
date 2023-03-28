package com.furkan.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.furkan.flagquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            if(binding.etName.text.isEmpty()){
                Toast.makeText(this, "Please Enter Your Name!", Toast.LENGTH_LONG).show()
            }else{
                Intent(this, QuizQuestionsActivity::class.java).apply {
                    putExtra("name", binding.etName.text.toString())
                    startActivity(this)
                    finish()
                }
            }
        }

    }
}