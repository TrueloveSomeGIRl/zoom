package com.cxw.didi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * 作者：cxw on 2021/7/12 10:10
 * 邮箱：848864817@qq.com
 */
class MainActivity2 : AppCompatActivity(), CoroutineScope by MainScope()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val lww = findViewById<TextView>(R.id.lw)

        lww.setOnClickListener {
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,lww
                ,
                "shareElement"
            ).toBundle()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent, bundle)
        }


    }
}