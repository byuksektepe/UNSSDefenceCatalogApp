package com.unss.defencecatalog.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun netCheck(context: Context): Boolean {
        val connMng = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connMng.activeNetworkInfo
        return netInfo != null && netInfo.isAvailable && netInfo.isConnected
    }
}