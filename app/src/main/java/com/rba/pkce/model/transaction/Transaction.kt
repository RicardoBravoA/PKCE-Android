package com.rba.pkce.model.transaction

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    @SerializedName("Data") val data : TransactionInfo
) : Parcelable