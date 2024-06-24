package com.magicianguo.fakeminotaur

import android.app.Application

class App : Application() {
    companion object {
        private lateinit var app: App
        fun getApp() = app
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}