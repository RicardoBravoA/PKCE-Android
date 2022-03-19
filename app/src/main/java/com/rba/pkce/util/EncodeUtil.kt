package com.rba.pkce.util

import android.util.Base64
import okhttp3.Request
import okio.Buffer
import java.util.Locale

object EncodeUtil {

    fun encode(request: Request): String {
        val newBody = if (request.body.toString() == "null") {
            "{}"
        } else {
            request.getBodyAsString().replace("\"", "")
        }

        val data = "${request.url.encodedPath}${request.method.lowercase(Locale.getDefault())}$newBody"
        return Base64.encodeToString(data.toByteArray(), Base64.NO_WRAP)
    }

    private fun Request.getBodyAsString(): String {
        val requestCopy = this.newBuilder().build()
        val buffer = Buffer()
        requestCopy.body?.writeTo(buffer)
        return buffer.readUtf8()
    }

}