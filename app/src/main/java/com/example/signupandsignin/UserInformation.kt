package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class UserInformation : AppCompatActivity() {

    private val userDBHelper by lazy { UserDBHelper(applicationContext) }
    lateinit var welcomeText: TextView
    lateinit var location: TextView
    lateinit var phone: TextView
    lateinit var signout: Button
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)
        title = "User Information"

        val extra = intent.extras
        if(extra != null){
            user = userDBHelper.getUserByUsername(extra.getString("username").toString())!!
        }

        welcomeText = findViewById(R.id.tvWelcome)
        location = findViewById(R.id.tvLocation)
        phone = findViewById(R.id.tvPhone)
        signout = findViewById(R.id.btnSignout)

        welcomeText.text = "Welcome, ${user.username}"
        location.text = "Location: ${user.location}"
        phone.text = "Phone: ${user.phone}"

        signout.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
    }
}