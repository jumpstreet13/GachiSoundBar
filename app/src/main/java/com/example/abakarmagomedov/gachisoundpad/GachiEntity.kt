package com.example.abakarmagomedov.gachisoundpad

import android.content.Context
import android.media.MediaPlayer
import java.lang.IllegalStateException

data class GachiEntity(val title: String, val rawSound: Int, var isOnRepeat: Boolean){

    private var mediaPlayer: MediaPlayer? = null

    fun getMediaPlayer(context: Context): MediaPlayer? {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, this.rawSound)
        }
        return mediaPlayer
    }
}