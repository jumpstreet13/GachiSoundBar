package com.abocha.abakarmagomedov.gachisoundpad

import android.animation.ArgbEvaluator
import android.view.View
import android.view.animation.Animation
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color


fun View.makeBlinkEffect(){
    val anim = ObjectAnimator.ofInt(
        this, "backgroundColor", Color.WHITE, Color.RED,
        Color.WHITE
    )
    anim.duration = 1500
    anim.setEvaluator(ArgbEvaluator())
    anim.repeatMode = ValueAnimator.REVERSE
    anim.repeatCount = Animation.INFINITE
    anim.start()
}