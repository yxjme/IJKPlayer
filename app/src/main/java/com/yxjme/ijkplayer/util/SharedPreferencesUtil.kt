package com.yxjme.ijkplayer.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import com.yxjme.ijkplayer.R

object SharedPreferencesUtil {

    val KEY_COLOR="key_backgroundColor"
    val VALUE = "value"


    enum class MODE{
        INT,STRING,BOOLEAN,FLOAT,LONG
    }


    /**
     * @param ctn
     * @param v
     */
    fun save(ctn:Context,v:Int){
        if (v!=null){
            val sharePre= ctn.getSharedPreferences(KEY_COLOR,Context.MODE_PRIVATE)
            val editor = sharePre.edit()
            editor.putInt(VALUE, v)
            editor.commit()
        }
    }


    /**
     *
     */
    fun read(ctn: Context, mode:MODE): Any {
        val sharePre= ctn.getSharedPreferences(KEY_COLOR,Context.MODE_PRIVATE)
        var result : Any
        when (mode){
            MODE.INT -> {
                result = sharePre.getInt(VALUE,0)
            }
            MODE.STRING ->{
                result = sharePre.getString(VALUE,"")!!
            }
            MODE.BOOLEAN ->{
                result = sharePre.getBoolean(VALUE,false)
            }
            MODE.FLOAT ->{
                result = sharePre.getFloat(VALUE,0f)
            }
            MODE.LONG ->{
                result = sharePre.getLong(VALUE,0)
            }
        }
        return result
    }




    /**
     * 切换主题
     */
    fun changeTheme(act:Activity){
        if (read(act, MODE.INT)==1){
            act.setTheme(R.style.AppTheme1)
        }else{
            act.setTheme(R.style.AppTheme)
        }
    }

}

