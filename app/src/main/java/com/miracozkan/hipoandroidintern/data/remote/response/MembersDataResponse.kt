package com.miracozkan.hipoandroidintern.data.remote.response

import com.google.gson.annotations.SerializedName


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 11:43          │
//└─────────────────────────────┘

data class MembersDataResponse(
    @SerializedName("company")
    val company: String,
    @SerializedName("members")
    val members: List<Member>,
    @SerializedName("team")
    val team: String
) {
    companion object {
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
    }
}