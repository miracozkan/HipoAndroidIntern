package com.miracozkan.hipoandroidintern.data.repository

import androidx.lifecycle.LiveData
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.util.Result


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:48          │
//└─────────────────────────────┘

interface MemberRepository {

    fun getAllTeamMembers(): LiveData<Result<List<Member>>>

    fun addNewMember(member: Member)

    fun searchText(search: String)

}