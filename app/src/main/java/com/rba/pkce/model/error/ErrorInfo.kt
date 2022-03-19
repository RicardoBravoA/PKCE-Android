package com.rba.pkce.model.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorInfo (
    val id: String = "100",
    val title: String = "I'm an error"
) : Parcelable