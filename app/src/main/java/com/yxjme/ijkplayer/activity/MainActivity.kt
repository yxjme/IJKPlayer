package com.yxjme.ijkplayer.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.yxjme.ijkplayer.R
import com.yxjme.ijkplayer.adapter.BaseAdapter
import com.yxjme.ijkplayer.data.VideoBean
import com.yxjme.ijkplayer.util.HorizontalLayoutManager
import com.yxjme.ijkplayer.util.SettingThemeView
import com.yxjme.ijkplayer.util.SharedPreferencesUtil
import com.yxjme.ijkplayer.util.SimpleDividerDecoration
import kotlinx.android.synthetic.main.ijk_video_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    var listUrl = mutableListOf<VideoBean>().apply {
        add(VideoBean("aac32-5","http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4"))
        add(VideoBean("sl","http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"))
        add(VideoBean("bipbopall","http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8"))
        add(VideoBean("prog_index","http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8"))
        add(VideoBean("oceans_aes","http://playertest.longtailvideo.com/adaptive/oceans_aes/oceans_aes.m3u8"))
        add(VideoBean("playlist","http://playertest.longtailvideo.com/adaptive/captions/playlist.m3u8"))
        add(VideoBean("playlist","http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8"))
        add(VideoBean("vM7nH0Kl","http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8"))
        add(VideoBean("f4v","http://cdn-fms.rbs.com.br/hls-vod/sample1_1500kbps.f4v.m3u8"))
        add(VideoBean("hls_sample1_manifest","http://cdn-fms.rbs.com.br/vod/hls_sample1_manifest.m3u8"))
        add(VideoBean("appleman","http://vevoplaylist-live.hls.adaptive.level3.net/vevo/ch1/appleman.m3u8"))
        add(VideoBean("ch1/appleman.m3u8","http://vevoplaylist-live.hls.adaptive.level3.net/vevo/ch1/appleman.m3u8"))
        add(VideoBean("ch2/appleman","http://vevoplaylist-live.hls.adaptive.level3.net/vevo/ch2/appleman.m3u8"))
        add(VideoBean("ch3/appleman","http://vevoplaylist-live.hls.adaptive.level3.net/vevo/ch3/appleman.m3u8"))
        add(VideoBean("website/m3u8/index","https://dl.dropboxusercontent.com/u/7303267/website/m3u8/index.m3u8"))
        add(VideoBean("vM7nH0Kl","http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8"))
        add(VideoBean("skate_phantom_flex_4k","http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8"))
        add(VideoBean("airshow/playlist","http://cdn3.viblast.com/streams/hls/airshow/playlist.m3u8"))
    }


    var listBanner = mutableListOf<String>()
        .apply {
            add("https://img30.360buyimg.com/babel/s590x470_jfs/t1/149111/33/688/96832/5ee77718Ee7c1fafc/56dffcc670b13bbf.jpg.webp")
            add("https://img10.360buyimg.com/pop/s590x470_jfs/t1/117556/14/10005/78842/5ee46623E2ccd1f1a/eb3a954d07294377.jpg.webp")
            add("https://imgcps.jd.com/ling/100004241290/5pav5Yev5aWH/5L2O6IezNOaKmA/p-5bd8253082acdd181d02f9e8/acbb51a5.jpg")
            add("https://img12.360buyimg.com/pop/s590x470_jfs/t1/135712/34/1910/75328/5ee1e77dEe0887f42/7075c561e4fe38f3.jpg.webp")
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(MainActivity@this)
        initView()
        SettingThemeView.newInstance().changeBackground(this)
    }



    fun initView(){
        setContentView(R.layout.ijk_video_layout)
        mNavigationView.setNavigationItemSelectedListener(Listener(this))
        initBanner()
        initList()
    }



    private fun initList() {
        val manager = LinearLayoutManager(this)
        val pSnaHelper = PagerSnapHelper()
        pSnaHelper.attachToRecyclerView(bannerRecyclerView)
        manager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = manager
        mRecyclerView.isNestedScrollingEnabled = false
        mRecyclerView.addItemDecoration(SimpleDividerDecoration())
        mRecyclerView.adapter = MyListAdapter(this,android.R.layout.test_list_item,listUrl.size)
    }




    private fun initBanner() {
        val manager= HorizontalLayoutManager()
        bannerRecyclerView.layoutManager = manager
        bannerRecyclerView.adapter = MyBannerAdapter(this,R.layout.item_banner_layout,listBanner.size)
    }




    inner class MyBannerAdapter(ctn: Context,itemView: Int, size: Int) : BaseAdapter(ctn, itemView, size) {
        var img : ImageView? = null
        override fun findViewHolder(itemView: View) {
            img = itemView.findViewById<ImageView>(R.id.im_show)
        }
        override fun childBindViewHolder(holder: MyViewHolder, p: Int) {
            Glide.with(ctn)
                .load(listBanner[p])
                .into(img)
        }
    }



    inner class MyListAdapter(ctn: Context,itemView: Int, size: Int) : BaseAdapter(ctn, itemView, size) {

        var text : TextView? = null

        override fun findViewHolder(itemView: View) {
            text = itemView.findViewById<TextView>(android.R.id.text1)
            text!!.gravity = Gravity.CENTER
            text!!.height = 120
        }

        override fun childBindViewHolder(holder: MyViewHolder, p: Int) {
            text!!.text = listUrl[p].videoTitle
            holder.itemView.setOnClickListener {
                val it = Intent()
                it.setClass(ctn, VideoPlayerActivity::class.java)
                it.putExtra(VideoPlayerActivity.videoUrl,listUrl[p].videoUrl)
                startActivity(it)
            }
        }
    }



    inner class Listener(var ctn: MainActivity) : NavigationView.OnNavigationItemSelectedListener{

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.share ->{
                    val it = Intent()
                    it.setClass(ctn, OneActivity::class.java)
                    startActivity(it)
                }

                R.id.backRound ->{
                    if (SharedPreferencesUtil.read(ctn, SharedPreferencesUtil.MODE.INT) ==1){
                        SharedPreferencesUtil.save(ctn, 0)
                        SettingThemeView.newInstance().settingThemeBgDrawable(mNavigationView, R.drawable.white_bg)
                    }else{
                        SharedPreferencesUtil.save( ctn,1)
                        SettingThemeView.newInstance().settingThemeBgDrawable(mNavigationView, R.mipmap.ic_launcher)
                    }
                    EventBus.getDefault().post("1")
                }
            }
            return false
        }
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
