package com.rba.pkce.model.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionInfo (
    val id : String,
    val message : String
) : Parcelable