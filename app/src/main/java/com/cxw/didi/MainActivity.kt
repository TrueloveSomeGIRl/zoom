package com.cxw.didi

import android.os.Bundle

import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

/**
 * 作者：cxw on 2021/7/12 10:15
 * 邮箱：848864817@qq.com
 */
class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    private lateinit var topBg: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clLayout = findViewById<LwCoordinatorLayout>(R.id.cl)
        val rlLayout = findViewById<FrameLayout>(R.id.rl)
        topBg = findViewById(R.id.top_bg)
        topBg.alpha = 0f
        val appbarLayout = findViewById<AppBarLayout>(R.id.appbar_layout)
        appbarLayout.addOnOffsetChangedListener(this)
        val llLayout = findViewById<LinearLayout>(R.id.ll_layout)
        val headImg = findViewById<ImageView>(R.id.head_img)
        clLayout.setUiView(rlLayout, llLayout, headImg)

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (verticalOffset < 0) {
            val i = verticalOffset.toFloat() / appBarLayout.height.toFloat()
            topBg.alpha = abs(i)
        }
    }

}