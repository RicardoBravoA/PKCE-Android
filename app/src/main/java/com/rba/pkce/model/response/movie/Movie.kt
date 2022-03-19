package com.rba.pkce.model.response.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id : Int,
    val title : String,
    val director : String,
    val year : Int,
    val rating : Double
) : Parcelable
