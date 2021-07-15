package com.example.embededsoftware.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.embededsoftware.MainActivity
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class loginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, loginScreen::class.java)
            startActivity(intent)
        }
        binding.btnJoin.setOnClickListener {
            val intent = Intent(this, joinScreen::class.java)
            startActivity(intent)
        }

        binding.btnNewJoin.setOnClickListener {

            auth.signInAnonymously()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                        }

                    }
        }
    }
}