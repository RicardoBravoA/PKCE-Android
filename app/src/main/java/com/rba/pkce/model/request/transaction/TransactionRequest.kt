package com.rba.pkce.model.request.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionRequest(
    val clientId : String,
    val amount : Double
) : Parcelable