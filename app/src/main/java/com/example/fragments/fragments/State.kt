package com.example.fragments.fragments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class State (
    var text: String,
    var visibility: Int
        ) : Parcelable
