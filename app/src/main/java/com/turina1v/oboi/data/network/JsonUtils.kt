package com.turina1v.oboi.data.network

import android.content.Context
import java.nio.charset.StandardCharsets

class JsonUtils {
    companion object {
        fun loadJsonFromAsset(context: Context, url: String): String? {
            var json: String? = null
            kotlin.runCatching {
                val inputStream = context.assets.open(url)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, StandardCharsets.UTF_8)
            }
            return json
        }
    }
}