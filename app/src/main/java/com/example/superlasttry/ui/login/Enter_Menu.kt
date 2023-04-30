package com.example.superlasttry.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.superlasttry.MainActivity
import com.example.superlasttry.R
import java.net.URL

class Enter_Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_menu)
        val button = findViewById<Button>(R.id.loginButton)
        val button1 = findViewById<Button>(R.id.student_button)
        val button2 = findViewById<Button>(R.id.guess_button)
        button2.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            button2.setBackgroundColor(
                ContextCompat.getColor(this,R.color.red2))

        }
        val Email = findViewById<EditText>(R.id.gmail)
        val email = Email.getText().toString()
        val Password = findViewById<EditText>(R.id.password)
        val password = Password.getText().toString()
        button1.setOnClickListener{
            button1.setBackgroundColor(
                ContextCompat.getColor(this,R.color.red2))
            showRegistration(button,email,password)
        }
        button.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            button.setBackgroundColor(
                ContextCompat.getColor(this,R.color.red2))
        }

        
    }


    private fun showRegistration(button: Button,email: String, password: String){
        val registrationBlank = findViewById<View>(R.id.registrationBlank)
        val registrMenu = findViewById<View>(R.id.registrMenu)
        registrationBlank.visibility=View.VISIBLE
        registrMenu.visibility=View.GONE
        button.setOnClickListener{
            GetStudentToken(email,password)

        }
    }

    private fun GetStudentToken(email: String, password: String){
        val textURL = "curl -X POST -d \"grant_type=password&username=<"+email+">&password=<"+password+">\" -u\"uEucBuuhbh3jKxHNbpd6TaiQC1x2xC8sNUCR9inW:PEf5ATD69gUmTFaPO4gx2Iq2uTZ5l68Rs19Pk3XdkrP5GsMUPj7khnKnxLnIBcRCvYOO5OUdX9qsc6vhveBsGaWIGWNIztNoFFb5LlkFY5W0DOcu7Lrgd7E36ecoy7a5\" https://sso.pnu.edu.ru/o/token/"
        val url = URL(textURL)

    }

}