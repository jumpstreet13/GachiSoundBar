package com.example.abakarmagomedov.gachisoundpad

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class GachiAdapter(layout: Int): BaseQuickAdapter<GachiEntity, BaseViewHolder>(layout) {


    override fun convert(helper: BaseViewHolder?, item: GachiEntity?) {
        helper?.getView<TextView>(R.id.gachiSongName)?.text = item?.title ?: "No title"
    }
}