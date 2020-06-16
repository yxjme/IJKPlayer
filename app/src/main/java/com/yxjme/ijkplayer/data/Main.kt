package com.yxjme.ijkplayer.data

import com.google.gson.Gson

fun main(args: Array<String>) {
    var map  = mutableMapOf<String,Any>()
    map.put("name","楊逍將")
    map.put("code",150)
    map.put("Msg","请求成功")
    val json : String = Gson().toJson(map)
    println("${json}")

    val bean = Gson().fromJson(json,UserBean::class.java)
    println("name = ${bean.name}\n code = ${bean.code}\n" +
            " msg = ${bean.Msg}")

    val bean1 = Gson().fromJson(json,UserResponse::class.java)
    println("name = ${bean1.name}\n code = ${bean1.code}\n" +
            " msg = ${bean1.Msg}")

}

