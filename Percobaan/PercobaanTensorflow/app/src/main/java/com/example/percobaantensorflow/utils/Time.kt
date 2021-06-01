package com.example.percobaantensorflow.utils

import android.icu.text.SimpleDateFormat
import java.sql.Date
import java.sql.Timestamp

object Time {

    fun timeStamp(): String {

        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("HH:mm")
        val time = sdf.format(Date(timeStamp.time))

        return time.toString()
    }

}