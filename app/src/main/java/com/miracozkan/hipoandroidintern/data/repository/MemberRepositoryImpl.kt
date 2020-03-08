package com.miracozkan.hipoandroidintern.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miracozkan.hipoandroidintern.data.remote.ProjectService
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.util.Result
import kotlinx.coroutines.*
import javax.inject.Inject
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

    private val teamMembersResponse = MutableLiveData<Result<List<Member>>>()

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
                    teamMembersResponse.postValue(Result.success(response.body()?.members.orEmpty()))
                } else {
                    teamMembersResponse.postValue(Result.error(response.message()))
                }
            } catch (e: Exception) {
                teamMembersResponse.postValue(Result.error(e.localizedMessage ?: "IOException"))
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}