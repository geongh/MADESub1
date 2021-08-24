package com.projk.madesub1.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies (
    val id: Int,
    val title: String,
    val releasedate: String,
    val storyline: String,
    val image: String,
    val isFavorite: Boolean
) : Parcelable