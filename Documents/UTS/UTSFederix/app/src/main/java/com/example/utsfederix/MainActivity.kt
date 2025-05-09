 package com.example.utsfederix
 import android.content.Intent
 import android.os.Bundle
 import android.os.Handler
 import androidx.appcompat.app.AppCompatActivity

 @Suppress("DEPRECATION")
 class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_splash_screen)

         Handler().postDelayed(Runnable {
             startActivity(Intent(this@MainActivity, LoginActivity::class.java))
             finish()
         }, 3000)
     }
 }