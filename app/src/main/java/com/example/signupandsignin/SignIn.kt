package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignIn : AppCompatActivity() {

    private val userDBHelper by lazy { UserDBHelper(applicationContext) }
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var signin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        title = "Sign In"

        usernameInput = findViewById(R.id.etUsernameSignIn)
        passwordInput = findViewById(R.id.etPasswordSignIn)
        signin = findViewById(R.id.btnSignIn)

        signin.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            if(username.isNotEmpty() && password.isNotEmpty()){
                val user = userDBHelper.getUserByUsername(username)
                if(user == null){
                    Toast.makeText(this,"Should Sign Up First", Toast.LENGTH_LONG).show()
                }else{
                    if(user.password == password){
                        val intent = Intent(this, UserInformation::class.java)
                        intent.putExtra("username", user.username)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Invalid Password", Toast.LENGTH_LONG).show()
                        passwordInput.requestFocus()
                    }
                }
            }else{
                Toast.makeText(this,"Enter Full Information", Toast.LENGTH_LONG).show()
            }
        }
    }
}