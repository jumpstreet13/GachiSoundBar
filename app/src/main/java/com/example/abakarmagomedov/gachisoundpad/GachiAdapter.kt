package com.example.abakarmagomedov.gachisoundpad

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import android.graphics.drawable.GradientDrawable
import android.media.MediaPlayer
import android.widget.ImageView
import androidx.cardview.widget.CardView


class GachiAdapter(layout: Int): BaseQuickAdapter<GachiEntity, BaseViewHolder>(layout) {


    override fun convert(helper: BaseViewHolder?, item: GachiEntity) {
        helper?.run {

            getView<TextView>(R.id.gachiSongName)?.text = item.title

            val gd = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(randInt(0, 999999), randInt(0, 999999))
            )
            gd.cornerRadius = 0f

            val songContainer = getView<ImageView>(R.id.cardBackground)
            songContainer.background = gd

            val cardView = getView<CardView>(R.id.songContainer)

            cardView.setOnClickListener {
                if(!item.isOnRepeat) {
                    val mediaPlayer: MediaPlayer? = item.getMediaPlayer(mContext)
                    mediaPlayer?.seekTo(0)
                    mediaPlayer?.start()
                }
            }

            cardView.setOnLongClickListener {
                val mediaPlayer: MediaPlayer? = item.getMediaPlayer(mContext)
                if(item.isOnRepeat){
                    mediaPlayer?.isLooping = false
                    item.isOnRepeat = false
                    mediaPlayer?.seekTo(0)
                    mediaPlayer?.pause()
                }else{
                    mediaPlayer?.run {
                        isLooping = true
                        item.isOnRepeat = true
                        if(!isPlaying){
                            start()
                        }
                        songContainer.makeBlinkEffect()
                    }
                }
                true
            }
        }
    }
}
