package com.example.justorder

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.justorder.data.restraurant.Restraurant
import com.example.justorder.databinding.ActivityMainBinding
import com.example.justorder.ui.Home
import com.example.justorder.ui.restraurant.RestraurantActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      val actionBar = supportActionBar
       actionBar!!.hide()
        binding.login.setOnClickListener{
         //   Toast.makeText(this,"ASHISH",Toast.LENGTH_LONG).show()
            startActivity(Intent(this@MainActivity,Home::class.java))

        }
        binding.crateAccount.setOnClickListener{
          //  Toast.makeText(this,"ASHISH",Toast.LENGTH_LONG).show()
            startActivity(Intent(this@MainActivity,Home::class.java))
        }
    }
}