package com.rba.pkce.model.response.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorModel(
    val id: String = "100",
    val message: String = "I'm an error"
) : Parcelable
