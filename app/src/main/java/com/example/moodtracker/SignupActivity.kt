package com.example.moodtracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //hides status bar


        val emailInput = findViewById<EditText>(R.id.etEmail)
        val passwordInput = findViewById<EditText>(R.id.etPassword)
        val signUpButton = findViewById<Button>(R.id.btnSignUp)
        val loginButton = findViewById<TextView>(R.id.btnLogin2)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        signUpButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val isUser = Database.users.any { it.first == email && it.second == password }

            if(isUser){ // if account already exists
                Toast.makeText(this, "Account already exists", Toast.LENGTH_SHORT).show()
            }

            if (validateEmail(email) && validatePassword(password) && !(isUser)) {
                Database.users.add(email to password)
                Toast.makeText(this, "Sign-Up Successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else {
                Toast.makeText(this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return email.matches(emailRegex)
    }

    private fun validatePassword(password: String): Boolean {
        // Minimum 8 characters, at least one uppercase, one lowercase, and one number
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")
        return password.matches(passwordRegex)
    }
}
