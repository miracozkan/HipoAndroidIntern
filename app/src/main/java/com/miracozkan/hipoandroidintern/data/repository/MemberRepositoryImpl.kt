package com.miracozkan.hipoandroidintern.data.repository

import androidx.lifecycle.MutableLiveData
import com.miracozkan.hipoandroidintern.common.BaseRepository
import com.miracozkan.hipoandroidintern.data.remote.ProjectService
import com.miracozkan.hipoandroidintern.data.remote.response.Member
import com.miracozkan.hipoandroidintern.util.Result
import com.miracozkan.hipoandroidintern.util.Status
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


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
) : MemberRepository, BaseRepository() {

    val teamMembersResponse = MutableLiveData<Result<List<Member>>>()

    private var memberList = ArrayList<Member>()
    private var filteredList = ArrayList<Member>()

    private var isFiltering = false
    private var searchingText = ""

    override suspend fun getAllTeamMembers() {
        val result = getResult {
            projectService.getMembersData()
        }
        if (result.status == Status.SUCCESS) {
            memberList = result.data?.members as ArrayList<Member>
            teamMembersResponse.postValue(Result.success(result.data.members.orEmpty()))
        } else {
            teamMembersResponse.postValue(Result.error(result.message))
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