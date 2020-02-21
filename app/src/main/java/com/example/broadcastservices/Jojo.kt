package com.example.broadcastservices

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Jojo(

    var name: String,
    var stand:String,
    var catchphrase:String,
    var part:String,
    var pic:String


):Parcelable