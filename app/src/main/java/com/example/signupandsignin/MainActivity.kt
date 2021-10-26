package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sign In
        findViewById<Button>(R.id.btnSignInActivity).setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
        }

        //Sign Up
        findViewById<Button>(R.id.btnSignUpActivity).setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
    }
}