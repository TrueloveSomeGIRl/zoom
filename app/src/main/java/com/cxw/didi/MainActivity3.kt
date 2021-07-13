package com.cxw.didi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity3 : AppCompatActivity() {
    private val time = mutableListOf(50, 40, 60, 55, 99, 88, 44, 30, 55, 44, 89, 45, 15,50, 40, 60, 55, 99, 88, 44, 30, 55, 44, 89, 45, 15)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main3)
//        val adapter = CountAdapter()
//        val rv = findViewById<RecyclerView>(R.id.rv)
//        val sw = findViewById<SwipeRefreshLayout>(R.id.sw)
//        rv.layoutManager = LinearLayoutManager(this)
//        rv.adapter = adapter
//        adapter.setNewInstance(time)
//        sw.setOnRefreshListener {
//            adapter.setNewInstance(time)
//            sw.isRefreshing=false
//        }
    }
}