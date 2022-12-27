package com.bumpy.sport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumpy.sport.Constants.USER_NAME
import com.bumpy.sport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.buttonStart.setOnClickListener {
                if (binding.etName.text?.toString()!!.isNotEmpty()){
                    val intent = Intent(this,QuestionActivity::class.java)
                    intent.putExtra(USER_NAME,binding.etName.text?.toString())
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Please fill the gaps", Toast.LENGTH_SHORT).show()
                }

            }


    }
}