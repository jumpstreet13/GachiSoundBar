package com.example.abakarmagomedov.gachisoundpad

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.media.MediaPlayer
import android.view.View
import android.view.animation.Animation
import java.lang.IllegalStateException

data class GachiEntity(val title: String, val rawSound: Int, var isOnRepeat: Boolean) {

    private var mediaPlayer: MediaPlayer? = null
    private var gradientDrawable: GradientDrawable? = null

    fun getGradientDrawable(): GradientDrawable? {
        if (gradientDrawable == null) {
            gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(randInt(0, 999999), randInt(0, 999999))
            )
            gradientDrawable?.cornerRadius = 0f
        }
        return gradientDrawable
    }

    fun getMediaPlayer(context: Context): MediaPlayer? {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, this.rawSound)
        }
        return mediaPlayer
    }
}