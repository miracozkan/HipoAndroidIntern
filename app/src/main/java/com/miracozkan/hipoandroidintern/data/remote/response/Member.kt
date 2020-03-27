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

data class Member(
    @SerializedName("age")
    val age: Int,
    @SerializedName("github")
    val github: String,
    @SerializedName("hipo")
    val hipo: Hipo,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String
)