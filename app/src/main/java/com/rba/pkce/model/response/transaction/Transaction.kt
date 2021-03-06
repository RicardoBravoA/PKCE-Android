package com.rba.pkce.model.response.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val id : String,
    val message : String
) : Parcelable