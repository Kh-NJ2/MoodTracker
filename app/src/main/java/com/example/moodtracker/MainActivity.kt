package com.example.moodtracker

import android.animation.ObjectAnimator
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var animatedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //hides status bar


        val sadFace = findViewById<ImageView>(R.id.sadFace)
        val neutralFace = findViewById<ImageView>(R.id.neutralFace)
        val happyFace = findViewById<ImageView>(R.id.happyFace)
        animatedTextView = findViewById(R.id.animatedTextView)

        neutralFace.alpha = 0f
        happyFace.alpha = 0f

        animateText("Every feeling counts ")

        //Fade out sad - fade in neutral
        val fadeOutSad = ObjectAnimator.ofFloat(sadFace, "alpha", 1f, 0f).apply {
            duration = 2000
        }
        val fadeInNeutral = ObjectAnimator.ofFloat(neutralFace, "alpha", 0f, 1f).apply {
            duration = 2000
            startDelay = 1500
        }

        //Fade out neutral - fade in happy
        val fadeOutNeutral = ObjectAnimator.ofFloat(neutralFace, "alpha", 1f, 0f).apply {
            duration = 2000
            startDelay = 2700
        }
        val fadeInHappy = ObjectAnimator.ofFloat(happyFace, "alpha", 0f, 1f).apply {
            duration = 2000
            startDelay = 4000
        }

        //last animation to end activity
        fadeInHappy.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            }
        })

        // Start animations
        fadeOutSad.start()
        fadeInNeutral.start()
        fadeOutNeutral.start()
        fadeInHappy.start()

    }

    private fun animateText(text: String) {
        animatedTextView.text = "" // Clear the TextView
        val delay: Long = 120 // Delay for each letter
        val handler = Handler(mainLooper)

        for (i in text.indices) {
            handler.postDelayed({
                animatedTextView.text = animatedTextView.text.toString() + text[i]
            }, delay * i)
        }
    }
}
