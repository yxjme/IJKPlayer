package com.yxjme.ijkplayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter(var ctn:Context,var itemView:Int,var size: Int) : RecyclerView.Adapter<BaseAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            findViewHolder(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val contentView  = LayoutInflater.from(ctn).inflate(itemView,parent,false)
        return  MyViewHolder(contentView)
    }

    override fun getItemCount(): Int {
        return size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        childBindViewHolder(holder,position)
    }

    abstract fun  findViewHolder(itemView: View)
    abstract fun  childBindViewHolder(holder: MyViewHolder,p:Int)
}