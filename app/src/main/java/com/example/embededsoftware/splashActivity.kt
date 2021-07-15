package com.example.embededsoftware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.embededsoftware.auth.loginActivity

class splashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            startActivity(Intent(this, loginActivity::class.java))

            finish()

        },3000)
    }
}