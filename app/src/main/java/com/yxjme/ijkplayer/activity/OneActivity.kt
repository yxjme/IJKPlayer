package com.yxjme.ijkplayer.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yxjme.ijkplayer.R
import com.yxjme.ijkplayer.util.SettingThemeView
import com.yxjme.ijkplayer.util.SharedPreferencesUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class OneActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        EventBus.getDefault().register(MainActivity@this)
        SettingThemeView.newInstance().changeBackground(this)
    }



    fun btn(v:View){
        MainActivity@this.startActivity(Intent(MainActivity@this,
            TweActivity::class.java))
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun mainThread(obj:Any){
        SettingThemeView.newInstance().changeBackground(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(MainActivity@this)
    }

}
