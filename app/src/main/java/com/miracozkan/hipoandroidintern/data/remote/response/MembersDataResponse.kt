package com.miracozkan.hipoandroidintern.data.remote.response


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 11:43          │
//└─────────────────────────────┘

data class MembersDataResponse(
    val company: String,
    val members: List<Member>,
    val team: String
)