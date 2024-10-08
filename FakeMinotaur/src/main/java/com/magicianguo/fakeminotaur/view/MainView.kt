package com.magicianguo.fakeminotaur.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import com.magicianguo.fakeminotaur.R
import com.magicianguo.fakeminotaur.util.SPUtils

class MainView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val mTvMain: TextView
    private val mRotateAnimator = ValueAnimator().apply {
        setFloatValues(-30F, 330F)
        repeatCount = ValueAnimator.INFINITE
        duration = 24 * 1000L
        interpolator = LinearInterpolator()
    }

    private val mRotateListener = ValueAnimator.AnimatorUpdateListener { animator ->
        onRotateUpdate(animator)
    }

    private val mScaleAnimator = ValueAnimator().apply {
        setFloatValues(1F, 0.6F, 1F)
        repeatCount = ValueAnimator.INFINITE
        duration = 20 * 1000L
        interpolator = LinearInterpolator()
    }

    private val mScaleListener = ValueAnimator.AnimatorUpdateListener { animator ->
        onScaleUpdate(animator)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_main_view, this)
        mTvMain = findViewById(R.id.tv_main)
        setBackgroundColor(context.getColor(R.color.main_background))
        mTvMain.setTypeface(Typeface.createFromAsset(context.assets, "JetBrainsMono-Bold.ttf"))
        mTvMain.text = "Loading..."
        postDelayed({
            mTvMain.text = getShowString()
        }, 2000L)
        mRotateAnimator.addUpdateListener(mRotateListener)
        mRotateAnimator.start()
        postDelayed({
            mScaleAnimator.addUpdateListener(mScaleListener)
            mScaleAnimator.start()
        }, 4000L)
    }

    private fun getShowString(): String {
        val textList = SPUtils.getTextList()
        textList.forEach { bt ->
            if (bt.selected) {
                return bt.text
            }
        }
        return "Fat Minotaur -27-\nAbnormal Environment"
    }

    fun setText(text: String) {
        mTvMain.text = text
    }

    private fun onRotateUpdate(animator: ValueAnimator) {
        val angle = animator.animatedValue as Float
        mTvMain.rotation = angle
    }

    private fun onScaleUpdate(animator: ValueAnimator) {
        val scale = animator.animatedValue as Float
        mTvMain.scaleX = scale
        mTvMain.scaleY = scale
    }

    companion object {
        private val mTextListeners = mutableListOf<ITextChangeListener>()
        fun register(listener: ITextChangeListener) {
            mTextListeners.add(listener)
        }

        fun unregister(listener: ITextChangeListener) {
            mTextListeners.remove(listener)
        }

        fun notifyTextChange(text: String) {
            mTextListeners.forEach {
                it.notifyTextChange(text)
            }
        }
    }

    interface ITextChangeListener {
        fun notifyTextChange(text: String)
    }
}