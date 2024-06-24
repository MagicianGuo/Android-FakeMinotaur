package com.magicianguo.fakeminotaur.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.magicianguo.fakeminotaur.R
import com.magicianguo.fakeminotaur.view.MainView

class MainActivity : AppCompatActivity() {
    private lateinit var mMainView: MainView
    private var mClickCount = 0
    private var mLastClickTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainView = findViewById(R.id.main_view)
        mMainView.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - mLastClickTime >= 300) {
                mClickCount = 0
            }
            mClickCount++
            mLastClickTime = currentTime
            if (mClickCount >= 5) {
                mClickCount = 0
                startActivity(Intent(this, SettingActivity::class.java))
            }
        }
    }
}