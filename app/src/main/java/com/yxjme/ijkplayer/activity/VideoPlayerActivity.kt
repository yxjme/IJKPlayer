package com.yxjme.ijkplayer.activity

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.yxjme.ijkplayer.R
import com.yxjme.ijkplayer.media.AndroidMediaController
import com.yxjme.ijkplayer.util.SettingThemeView
import kotlinx.android.synthetic.main.activity_video_player.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class VideoPlayerActivity : AppCompatActivity() {


    private var url = ""
    private var isScreenFull = false


    companion object {
        val videoUrl="url"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_video_player)
        SettingThemeView.newInstance().changeBackground(this)
        init()
    }


    fun btn(v: View){
        if (isScreenFull){
            exitFull()
        }else{
            screenFull()
        }
    }



    fun setBtnText(){
        if (isScreenFull) btnText.text = "退出全屏" else btnText.text = "全屏"
    }



    fun screenFull(){
        container.removeView(mIjkVideoView)
        var deView = window.decorView as FrameLayout
        val p = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        mIjkVideoView.layoutParams = p
        deView.removeView(mIjkVideoView)
        deView.addView(mIjkVideoView,0)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        isScreenFull = true
        setBtnText()
    }


    fun exitFull(){
        var deView = window.decorView as FrameLayout
        deView.removeView(mIjkVideoView)
        val p = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        mIjkVideoView.layoutParams = p
        container.addView(mIjkVideoView,0)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        isScreenFull = false
        setBtnText()
    }



    private fun init() {
        url = intent.getStringExtra(videoUrl)
        val mMediaController = AndroidMediaController(this, false)
        mIjkVideoView.setMediaController(mMediaController)
        mIjkVideoView.setVideoPath(url)
        mIjkVideoView.start()
    }


    override fun onStop() {
        super.onStop()
        /**
         *  点击返回或不允许后台播放时 释放资源
         */
        if (mIjkVideoView!!.isBackgroundPlayEnabled) {
            mIjkVideoView.stopPlayback()
            mIjkVideoView.release(true)
            mIjkVideoView.stopBackgroundPlay()
        } else {
            mIjkVideoView.enterBackground()
        }
        IjkMediaPlayer.native_profileEnd()
    }

    override fun onDestroy() {
        super.onDestroy()
        mIjkVideoView.stopPlayback()
        mIjkVideoView.release(true)
    }
}