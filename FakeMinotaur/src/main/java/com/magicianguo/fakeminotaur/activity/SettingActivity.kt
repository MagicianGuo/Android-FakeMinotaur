package com.magicianguo.fakeminotaur.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magicianguo.fakeminotaur.R
import com.magicianguo.fakeminotaur.adapter.TextListAdapter
import com.magicianguo.fakeminotaur.util.SPUtils

class SettingActivity : AppCompatActivity() {
    private lateinit var mRvList: RecyclerView
    private val mAdapter = TextListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mRvList = findViewById(R.id.rv_list)
        mRvList.layoutManager = LinearLayoutManager(this)
        mRvList.itemAnimator = null
        mRvList.adapter = mAdapter
        mAdapter.updateList(SPUtils.getTextList())
    }
}