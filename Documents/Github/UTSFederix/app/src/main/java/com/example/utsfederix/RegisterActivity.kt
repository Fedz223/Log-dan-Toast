package com.example.utsfederix

import com.example.utsfederix.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName = findViewById<EditText?>(R.id.etName)
        val btnRegister = findViewById<Button?>(R.id.btnRegister)
        val etPassword = findViewById<EditText>(R.id.etPassword)
      btnRegister.setOnClickListener(View.OnClickListener { v: View? ->

            val name = etName.getText().toString()
          val password = etPassword.text.toString()
          val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
          with(sharedPref.edit()) {
              putString("userId", name)
              putString("password", password)
              apply()
}

            Log.d(TAG, "Register button clicked with name: " + name)


            Toast.makeText(this, "Registrasi untuk " + name, Toast.LENGTH_SHORT).show()


            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_LONG).show()


            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        })
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
}
