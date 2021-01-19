package com.abocha.abakarmagomedov.gachisoundpad

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import android.media.MediaPlayer
import android.widget.ImageView
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class GachiAdapter(layout: Int) : BaseQuickAdapter<GachiEntity, BaseViewHolder>(layout) {

    private val animatorsMap = mutableMapOf<ImageView, ObjectAnimator>()


    override fun convert(helper: BaseViewHolder, item: GachiEntity) {
        helper.run {
            getView<TextView>(R.id.gachiSongName).text = item.title

            val songContainer = getView<ImageView>(R.id.cardBackground)
            var animator = animatorsMap[songContainer]
            if (animator == null) {
                animator = ObjectAnimator.ofInt(
                    songContainer, "backgroundColor", Color.WHITE, Color.RED,
                    Color.WHITE
                )
                animator.duration = 1500
                animator.setEvaluator(ArgbEvaluator())
                animator.repeatMode = ValueAnimator.REVERSE
                animator.repeatCount = ValueAnimator.INFINITE
                animatorsMap[songContainer] = animator
            }

            if (!item.isOnRepeat) {
                animator?.pause()
                songContainer.background = item.getGradientDrawable()
            } else {
                animator?.start()
            }

            songContainer.setOnClickListener {
                if (!item.isOnRepeat) {
                    val mediaPlayer: MediaPlayer? = item.getMediaPlayer(context)
                    mediaPlayer?.seekTo(0)
                    mediaPlayer?.start()
                    animator?.pause()
                }
            }

            songContainer.setOnLongClickListener {
                val mediaPlayer: MediaPlayer? = item.getMediaPlayer(context)
                if (item.isOnRepeat) {
                    mediaPlayer?.isLooping = false
                    item.isOnRepeat = false
                    mediaPlayer?.seekTo(0)
                    mediaPlayer?.pause()
                    animator?.pause()
                    songContainer.background = item.getGradientDrawable()
                } else {
                    mediaPlayer?.run {
                        isLooping = true
                        item.isOnRepeat = true
                        if (!isPlaying) {
                            start()
                        }
                        animator?.start()
                    }
                }
                true
            }
        }
    }
}
