package com.miracozkan.hipoandroidintern.data.repository

import com.miracozkan.hipoandroidintern.data.remote.response.Member


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:48          │
//└─────────────────────────────┘

interface MemberRepository {

    suspend fun getAllTeamMembers()

    fun addNewMember(member: Member)

    fun searchText(search: String)

}