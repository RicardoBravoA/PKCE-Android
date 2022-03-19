package com.rba.pkce.model.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("Data") val data : List<MovieInfo>
) : Parcelable
