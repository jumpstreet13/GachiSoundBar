package com.example.abakarmagomedov.gachisoundpad

import java.util.*

fun randInt(min: Int, max: Int): Int {
    val rand = Random()
    return rand.nextInt() * (max - min) + min
}
