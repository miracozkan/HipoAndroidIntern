package com.miracozkan.hipoandroidintern.ui.member_search

import androidx.lifecycle.ViewModel
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.data.repository.MemberRepositoryImpl
import com.miracozkan.hipoandroidintern.di.NetworkStateMonitor
import javax.inject.Inject


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:45          │
//└─────────────────────────────┘

class MemberSearchViewModel @Inject constructor(
    private val memberRepositoryImpl: MemberRepositoryImpl,
    private val networkStateMonitor: NetworkStateMonitor
) : ViewModel() {

    private val _teamMembersResult = memberRepositoryImpl.getAllTeamMembers()
    val teamMembers get() = _teamMembersResult

    fun addNewMember(member: Member) {
        memberRepositoryImpl.addNewMember(member)
    }

    fun searchList(search: String) {
        memberRepositoryImpl.searchText(search)
    }

    private val _networkState = networkStateMonitor
    val networkState get() = _networkState
}