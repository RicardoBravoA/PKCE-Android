package com.rba.pkce.util

import android.util.Base64
import java.util.*

object EncodeUtil {

    fun encode(path: String, method: String, body: String): String {

        val newBody = if (body == "null") {
            "{}"
        } else {
            body
        }

        val data = "$path${method.lowercase(Locale.getDefault())}$newBody"
        return Base64.encodeToString(data.toByteArray(), Base64.NO_WRAP)
    }

}