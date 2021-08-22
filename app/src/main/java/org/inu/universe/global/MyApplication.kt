package org.inu.universe.global

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

// 나중에 수정해야할 곳
@SuppressLint("StaticFieldLeak")
object MyApplication {
    var context: Context? = null
}