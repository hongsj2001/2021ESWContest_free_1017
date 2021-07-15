package com.example.embededsoftware.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.embededsoftware.MainActivity
import com.example.embededsoftware.R
import com.example.embededsoftware.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class loginScreen : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private lateinit var binding : ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login_screen)

        binding.btnLoginLogin.setOnClickListener{

            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this, MainActivity::class.java)

                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)

                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

                        } else {
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                        }
                    }

        }
    }
}