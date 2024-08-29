package com.magicianguo.fakeminotaur.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.magicianguo.fakeminotaur.App
import com.magicianguo.fakeminotaur.bean.BeanText
import java.lang.reflect.Type

object SPUtils {
    private val mSP = App.getApp().getSharedPreferences("SPUtils", Context.MODE_PRIVATE)
    private val mGson = Gson()
    private const val KEY_TEXT_LIST = "KEY_TEXT_LIST"

    fun setTextList(list: List<BeanText?>) {
        TaskPool.CACHE.execute {
            val edit = mSP.edit()
            val type: Type = object : TypeToken<List<BeanText>>() {}.type
            val json = mGson.toJson(list, type)
            edit.putString(KEY_TEXT_LIST, json)
            edit.apply()
        }
    }

    fun getTextList(): List<BeanText> {
        val json = mSP.getString(KEY_TEXT_LIST, "[{\"selected\":true,\"text\":\"Fat Minotaur -27-\\nAbnormal Environment\"},{\"selected\":false,\"text\":\"Sad Minotaur -25-\\nConventional Tests (8)\"},null]")
        val type: Type = object : TypeToken<List<BeanText>>() {}.type
        return mGson.fromJson(json, type)
    }

}