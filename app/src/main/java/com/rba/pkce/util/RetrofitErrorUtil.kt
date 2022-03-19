package com.rba.pkce.util

import com.rba.pkce.model.error.ErrorModel
import com.rba.pkce.networking.ApiManager
import retrofit2.Response
import java.io.IOException

object RetrofitErrorUtil {
    fun parseError(response: Response<*>): ErrorModel? {

        val converter = ApiManager.retrofit.responseBodyConverter<ErrorModel>(
            ErrorModel::class.java,
            arrayOfNulls<Annotation>(0)
        )

        val errorModel: ErrorModel

        try {
            errorModel = converter.convert(response.errorBody()!!)!!
        } catch (e: IOException) {
            return ErrorModel()
        }

        return errorModel
    }
}