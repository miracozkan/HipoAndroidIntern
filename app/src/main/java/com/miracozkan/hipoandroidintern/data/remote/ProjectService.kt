package com.miracozkan.hipoandroidintern.data.remote

import com.miracozkan.hipoandroidintern.data.remote.response.MembersDataResponse
import retrofit2.Response
import retrofit2.http.GET


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:43          │
//└─────────────────────────────┘

interface ProjectService {
    @GET("hipo.json")
    suspend fun getMembersData(): Response<MembersDataResponse>
}