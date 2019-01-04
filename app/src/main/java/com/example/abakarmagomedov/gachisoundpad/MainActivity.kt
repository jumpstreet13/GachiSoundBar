package com.example.abakarmagomedov.gachisoundpad

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.raw
import android.media.MediaPlayer
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var gachiAdapter: GachiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gachiAdapter = GachiAdapter(R.layout.item_gachi_sound_tab)
        with(gachiList) {
            adapter = gachiAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        listRaw()
        gachiAdapter.onItemClickListener =
                BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                    var mediaPlayer: MediaPlayer? = null
                    adapter?.let {
                        mediaPlayer = MediaPlayer.create(this@MainActivity, (it.getItem(position) as GachiEntity).rawSound)
                    }
                    mediaPlayer?.start()
                }
    }

    fun listRaw() {
        val fields = R.raw::class.java.fields
        val gachiList = mutableListOf<GachiEntity>()
        for (count in fields.indices) {
            Log.i("Raw Asset: ", fields[count].getName())
            val field = fields[count]
            gachiList.add(GachiEntity(field.name, field.getInt(fields[count]), false))
        }
        gachiAdapter.setNewData(gachiList)
    }
}
