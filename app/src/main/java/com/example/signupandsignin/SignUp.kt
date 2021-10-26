package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUp : AppCompatActivity() {

    private val userDBHelper by lazy { UserDBHelper(applicationContext) }
    lateinit var usernameInput: EditText
    lateinit var locationInput: EditText
    lateinit var phoneInput: EditText
    lateinit var passwordInput: EditText
    lateinit var signup: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        title = "Sign Up"

        usernameInput = findViewById(R.id.etUsernameSignUp)
        locationInput = findViewById(R.id.etLocationSignUp)
        phoneInput = findViewById(R.id.etPhoneSignUp)
        passwordInput = findViewById(R.id.etPasswordSignUp)
        signup = findViewById(R.id.btnSignUp)

        signup.setOnClickListener {
            val username = usernameInput.text.toString()
            val location = locationInput.text.toString()
            val phone = phoneInput.text.toString()
            val password = passwordInput.text.toString()
            if(username.isNotEmpty() && location.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()){
                val isUsed = userDBHelper.getUserByUsername(username)
                if(isUsed == null){
                    val user = User(0,username,location,phone.toInt(),password)
                    val result = userDBHelper.addNewUser(user)
                    if(result > -1){
                        Toast.makeText(this,"Added", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, UserInformation::class.java)
                        intent.putExtra("username", user.username)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Try Again", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this,"Username is Used", Toast.LENGTH_LONG).show()
                    usernameInput.requestFocus()
                }
            }else{
                Toast.makeText(this,"Enter Full Information", Toast.LENGTH_LONG).show()
            }
        }
    }
}