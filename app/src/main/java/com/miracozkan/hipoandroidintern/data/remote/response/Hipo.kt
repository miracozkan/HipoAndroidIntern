package com.miracozkan.hipoandroidintern.data.remote.response

import com.google.gson.annotations.SerializedName


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 11:42          │
//└─────────────────────────────┘

data class Hipo(
    @SerializedName("position")
    val position: String,
    @SerializedName("years_in_hipo")
    val yearsInHipo: Int
)