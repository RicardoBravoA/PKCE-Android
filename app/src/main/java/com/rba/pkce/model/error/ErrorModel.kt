package com.rba.pkce.model.error

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorModel(
    @SerializedName("ErrorModel") val error : ErrorInfo = ErrorInfo()
) : Parcelable
