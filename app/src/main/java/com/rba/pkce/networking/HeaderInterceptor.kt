package com.rba.pkce.networking

import com.rba.pkce.util.EncodeUtil
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()
            .header("request-id",
                EncodeUtil.encode(chain.request().url.encodedPath, chain.request().method, chain.request().body.toString()))
            .build()

        return chain.proceed(requestBuilder)
    }
}