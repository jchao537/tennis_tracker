package com.example.tennis_tracker

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.text.HtmlCompat
import com.example.tennis_tracker.database.Player

fun formatPlayers(players: List<Player>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("ALL PLAYERS")
        players.forEach {
            append("<br>")
            append("Player: " + it.playerName + " Year: " + it.playerYear + " Rank: " + it.playerRank)
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun hideSoftKeyboard(activity: Activity) {
    if (activity.currentFocus == null) {
        return
    }
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
}


