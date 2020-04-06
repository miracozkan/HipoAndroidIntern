package com.miracozkan.hipoandroidintern.ui.member_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.data.repository.MemberRepositoryImpl
import com.miracozkan.hipoandroidintern.di.NetworkStateMonitor
import com.miracozkan.hipoandroidintern.util.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    private var gettingMemberJob: Job? = null
    lateinit var teamMembers: LiveData<Result<List<Member>>>

    init {
        getTeamMembers()
    }

    private fun getTeamMembers() {
        if (gettingMemberJob?.isActive == true) {
            return
        }
        gettingMemberJob = launchGettingMemberJob()
    }

    private fun launchGettingMemberJob(): Job? {
        return viewModelScope.launch {
            teamMembers = liveData {
                memberRepositoryImpl.getAllTeamMembers()
                emit(Result.loading())
                emitSource(memberRepositoryImpl.teamMembersResponse)
            }
        }
    }

    fun addNewMember(member: Member) {
        memberRepositoryImpl.addNewMember(member)
    }

    fun searchList(search: String) {
        memberRepositoryImpl.searchText(search)
    }

    private val _networkState = networkStateMonitor
    val networkState get() = _networkState
}