package com.example.implicitfederix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val websiteEdit: EditText = findViewById(R.id.website_edit_text)
        val openWebsiteButton: Button = findViewById(R.id.open_website_button)
        openWebsiteButton.setOnClickListener{
            val websiteurl = websiteEdit.text.toString()
            openWebsite(websiteurl)
        }

        val locationEdit: EditText = findViewById(R.id.location_edit_text)
        val locationButton: Button = findViewById(R.id.location_button)
        locationButton.setOnClickListener{
            val location = locationEdit.text.toString()
            openLocation(location)
        }

        val shareEdit: EditText = findViewById(R.id.share_edit_text)
        val shareButton: Button = findViewById(R.id.share_text_button)
        shareButton.setOnClickListener{
            val text = shareEdit.text.toString()
        }

        val implicitEdit: EditText = findViewById(R.id.implicit_edit_text)
        val implicitButton: Button = findViewById(R.id.implicit_button)
        implicitButton.setOnClickListener {
            val sendText = implicitEdit.text.toString()
            sendData(sendText)  // Panggil fungsi sendData untuk mengirim ke SecondActivity
        }


    }

    private fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        Log.v("cek url", url)
        Toast.makeText(applicationContext, url, Toast.LENGTH_LONG).show()
        startActivity(intent)

    }

    private fun openLocation(location: String) {
        val uri = Uri.parse("geo:0,0?q=$location")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        Log.v( "cek location", location)
        Toast.makeText(applicationContext, location, Toast.LENGTH_LONG).show()
        startActivity(intent)
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        Log.v("cek string", text)
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        shareText(text)
        startActivity(Intent.createChooser(intent, "Share Text"))
    }
    private fun sendData(text: String) {
        val intent = Intent(this, SecondActivity:: class.java)
        intent.putExtra("ini dikirim", text)
        startActivity(intent)
    }

}
