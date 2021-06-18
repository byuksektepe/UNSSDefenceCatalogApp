package com.unss.defencecatalog.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideUtil {
    fun hiddenImage(context: Context?, url: String?, image: ImageView?) {
        Glide.with(context!!)
            .load(url)
            .centerCrop()
            .into(image!!)
    }
}