package com.gnardini.marketoffers.extensions

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.startActivity(clazz: Class<out Activity>) = startActivity(Intent(this, clazz))

fun AppCompatActivity.startActivityClearingTask(clazz: Class<out Activity>) {
    val intent = Intent(this, clazz)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}
