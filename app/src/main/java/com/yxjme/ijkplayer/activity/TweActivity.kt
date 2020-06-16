package com.yxjme.ijkplayer.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yxjme.ijkplayer.R
import com.yxjme.ijkplayer.util.SettingThemeView
import com.yxjme.ijkplayer.util.SharedPreferencesUtil

class TweActivity : AppCompatActivity() {

    var savedInstanceState : Bundle ? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        SharedPreferencesUtil.changeTheme(this)
        setContentView(R.layout.activity_twe)
        SettingThemeView.newInstance().changeBackground(this)
    }

}
