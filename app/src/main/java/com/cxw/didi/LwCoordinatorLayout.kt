package com.cxw.didi

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * 作者：cxw on 2021/7/12 10:02
 * 邮箱：848864817@qq.com
 */
class LwCoordinatorLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {

    private var mZoomView: View? = null
    private var mTopMoveGroup: View? = null
    private var mDownMoveGroup: View? = null

    private val mScrollFactor = 0.4f //缩放系数，缩放系数越大，变化的越大
    private val mReplyFactor = 0.4f //回调系数，越大，回调越慢
    private var mZoomViewWidth = 0
    private var mZoomViewHeight = 0
    private var downY = 0f


    fun setUiView(topGroup: View, downGroup: View,mZoomView: View) {
        mTopMoveGroup = topGroup
        mDownMoveGroup = downGroup
        this.mZoomView = mZoomView
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mZoomView?.let {
            mZoomViewWidth = it.measuredWidth
            mZoomViewHeight = it.measuredHeight
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> downY = ev.y
            MotionEvent.ACTION_MOVE -> if (ev.y > downY) {
                val distance = (ev.y - downY) * mScrollFactor
                setZoom(distance)
            }
            MotionEvent.ACTION_UP -> if (ev.y > downY) replyAnim()
        }
        return super.dispatchTouchEvent(ev)
    }


    private fun replyAnim() {
        val w = mZoomView?.let { measuredWidth } ?: 0
        val distance = (w - mZoomViewWidth).toFloat()
        with(ValueAnimator.ofFloat(distance, 0f)) {
            duration = (distance * mReplyFactor).toLong()
            addUpdateListener { animation -> setZoom(animation.animatedValue as Float) }
            start()
        }
    }

    private fun setZoom(zoom: Float) {
        mZoomView?.let { zoomView ->
            val lp = zoomView.layoutParams
            lp.width = (mZoomViewWidth * ((mZoomViewWidth + zoom) / mZoomViewWidth)).toInt()
            lp.height = (mZoomViewHeight * ((mZoomViewWidth + zoom) / mZoomViewWidth)).toInt()
            (lp as MarginLayoutParams).setMargins(-(lp.width - mZoomViewWidth) / 2, 0, 0, 0)
            mZoomView?.layoutParams = lp
            try {
                val parent = mTopMoveGroup?.parent as CollapsingToolbarLayout
                val layoutParams = parent.layoutParams
                layoutParams.height = lp.height
                parent.layoutParams = layoutParams
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}