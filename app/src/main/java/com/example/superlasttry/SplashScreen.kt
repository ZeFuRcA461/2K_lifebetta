package com.example.superlasttry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.superlasttry.ui.login.Enter_Menu

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val iv_note = findViewById<View>(R.id.iv_note)
        iv_note.alpha = 0f
        iv_note.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this, Enter_Menu::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

    }
}