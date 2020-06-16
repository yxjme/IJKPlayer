package com.yxjme.ijkplayer.activity

import android.app.Application
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class MyApp : Application() {


    companion object{
        private var instance : Application? = null
        fun instance() = instance!!
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    /**
     * 初始化
     */
    private fun init(){
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }


}