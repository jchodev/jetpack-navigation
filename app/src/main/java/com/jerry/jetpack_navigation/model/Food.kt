package com.jerry.jetpack_navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food (
    val name: String = "",
    val price: String = "",
) : Parcelable