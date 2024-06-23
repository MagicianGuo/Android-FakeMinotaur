package com.magicianguo.fakeminotaur

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView

class MainView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val mTvMain: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_main_view, this)
        mTvMain = findViewById(R.id.tv_main)
        setBackgroundColor(context.getColor(R.color.main_background))
        mTvMain.setTypeface(Typeface.createFromAsset(context.assets, "JetBrainsMono-Bold.ttf"))
        mTvMain.text = "Loading..."
        postDelayed({
            mTvMain.text = "Sad Minotaur -25-\nConventional Tests (8)"
        }, 2000L)
    }
}