package com.asyfdzaky.notifcounterlikedislike

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class NotifReceiver:BroadcastReceiver (){
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val prefs: SharedPreferences = context.getSharedPreferences("CounterPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit() // Membuat objek editor untuk mengubah nilai SharedPreferences
        var isUpdated = false

        when (action) {
            "ACTION_LIKE" -> {
                val likeCount = prefs.getInt("LIKE_COUNT", 0) + 1
                editor.putInt("LIKE_COUNT", likeCount)
                isUpdated = true
            }
            "ACTION_DISLIKE" -> {
                val dislikeCount = prefs.getInt("DISLIKE_COUNT", 0) + 1
                editor.putInt("DISLIKE_COUNT", dislikeCount)
                isUpdated = true
            }
        }
        if (isUpdated) {
            editor.apply()
            // Mengirimkan broadcast untuk memperbarui UI di MainActivity
            val updateIntent = Intent("ACTION_UPDATE_COUNTERS")
            context.sendBroadcast(updateIntent)
            // Setelah nilai counter berhasil diperbarui, kita mengirimkan broadcast baru dengan action "ACTION_UPDATE_COUNTERS".
           // Dengan mengirimkan broadcast ini, kita memberi tahu aplikasi bahwa nilai counter telah berubah dan MainActivity perlu memperbarui tampilan UI-nya.
        }
    }
}