package com.miracozkan.hipoandroidintern.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miracozkan.hipoandroidintern.data.remote.ProjectService
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.util.Result
import com.miracozkan.hipoandroidintern.util.SPACE
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:49          │
//└─────────────────────────────┘

class MemberRepositoryImpl @Inject constructor(
    private val projectService: ProjectService
) : MemberRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    private val teamMembersResponse = MutableLiveData<Result<List<Member>>>()

    private var memberList = ArrayList<Member>()
    private var filteredList = ArrayList<Member>()

    private var isFiltering = false
    private var searchingText = SPACE

    override fun getAllTeamMembers(): LiveData<Result<List<Member>>> {
        launch {
            fetchAllTeamMembers()
        }
        return teamMembersResponse
    }

    private suspend fun fetchAllTeamMembers() {
        withContext(Dispatchers.IO) {
            try {
                teamMembersResponse.postValue(Result.loading())
                val response = projectService.getMembersData()
                if (response.isSuccessful) {
                    memberList = response.body()?.members as ArrayList<Member>
                    teamMembersResponse.postValue(Result.success(memberList))
                } else {
                    teamMembersResponse.postValue(Result.error(response.message()))
                }
            } catch (e: Exception) {
                teamMembersResponse.postValue(Result.error())
                Log.e("Repository Exception:", e.localizedMessage ?: "Exception")
            }
        }
    }

    override fun addNewMember(member: Member) {
        if (isFiltering) {
            searchList(searchingText, member)
        } else {
            memberList.add(member)
            teamMembersResponse.postValue(Result.success(memberList))
        }
    }

    override fun searchText(search: String) {
        searchingText = search
        searchList(search)
    }

    private fun searchList(search: String, member: Member? = null) {
        filteredList = if (search.isEmpty() or search.isBlank()) {
            isFiltering = false
            memberList
        } else {
            isFiltering = true
            member?.let {
                memberList.add(it)
            }
            val resultList = arrayListOf<Member>()
            for (row in memberList) {
                if (row.name.toLowerCase(Locale.getDefault()).contains(search)) {
                    resultList.add(row)
                }
            }
            resultList
        }
        teamMembersResponse.postValue(Result.success(filteredList))
    }
}