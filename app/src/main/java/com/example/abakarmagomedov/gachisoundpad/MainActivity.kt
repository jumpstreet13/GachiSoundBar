package com.example.abakarmagomedov.gachisoundpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.raw
import android.media.MediaPlayer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(this@MainActivity, 2)
        }
        listRaw()
    }

    private fun listRaw() {
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
