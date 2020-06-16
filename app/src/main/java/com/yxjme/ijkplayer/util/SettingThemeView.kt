package com.yxjme.ijkplayer.util

import android.app.Activity
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.yxjme.ijkplayer.R

class SettingThemeView private constructor(){


    companion object{
        private var bgDrawable :Int = 0
        private var instance : SettingThemeView? = null
            get() {
                if (field==null){
                    field = SettingThemeView()
                }
                return field
            }

        fun newInstance(): SettingThemeView {
            return instance!!
        }
    }




    /**
     */
    fun settingThemeBgColor(v: View,@ColorInt bgColor :Int) {
        v.setBackgroundColor(bgColor)
    }


    /**
     */
    fun settingThemeBgDrawable(v: View,@DrawableRes bgDrawable :Int) {
        v.setBackgroundResource(bgDrawable)
        SettingThemeView.bgDrawable = bgDrawable
    }






    /**
     *
     */
    fun changeBackground(ctn: Activity){
        val view = ctn.window.decorView
        if (SharedPreferencesUtil.read(ctn,SharedPreferencesUtil.MODE.INT)==0){
            bgDrawable = R.drawable.white_bg
        }else{
            bgDrawable = R.mipmap.ic_launcher
        }
        settingThemeBgDrawable(view,bgDrawable)
    }




    /*设置批量*/
    var list : Collection<View>? = null


    /**
     */
    fun settingThemeViews(vararg v: View) : SettingThemeView{
        list = mutableListOf()
        for (it in v){
            (list as MutableList<View>).add(it)
        }
        return SettingThemeView@this
    }



    /**
     * 开始颜色
     */
    fun settingThemeColor( @ColorInt bgColor:Int){
        if (list!=null){
            for (i in list!!){
                i.setBackgroundColor(bgColor)
            }
        }
    }



    /**
     * 开始颜色
     */
    fun settingThemeDrawable(@DrawableRes bgDrawable:Int){
        if (list!=null){
            for (i in list!!){
                i.setBackgroundResource(bgDrawable)
            }
        }
    }
}