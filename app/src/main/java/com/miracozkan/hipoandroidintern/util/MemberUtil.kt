package com.miracozkan.hipoandroidintern.util

import com.miracozkan.hipoandroidintern.data.remote.response.Hipo
import com.miracozkan.hipoandroidintern.data.remote.response.Member

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 18:23          │
//└─────────────────────────────┘

fun generateNewMember(): Member {
    val hipo = Hipo(
        "Intern",
        0
    )
    return Member(
        21,
        "miracozkan",
        hipo,
        "Istanbul",
        "Mirac Ozkan"
    )
}