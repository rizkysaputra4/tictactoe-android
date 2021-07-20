package com.training.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_page.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        btnStartGame.setOnClickListener {
            this.toGameActivity()
        }
    }

    fun toGameActivity() {
        Intent(this, GameActivity::class.java).also { intent ->
            intent.putExtra("P1", editTextPlayerName1.text.toString())
            intent.putExtra("P2", editTextPlayerName2.text.toString())

            startActivity(intent)
        }
    }
}