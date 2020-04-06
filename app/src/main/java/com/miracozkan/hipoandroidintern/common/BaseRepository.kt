package com.miracozkan.hipoandroidintern.common

import com.miracozkan.hipoandroidintern.util.Result
import retrofit2.Response


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 06.04.2020 - 18:26          │
//└─────────────────────────────┘

abstract class BaseRepository {

    internal suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.success(body)!!
                }
            }
            return error(response.message(), response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), e.hashCode())
        }
    }

    private fun <T> error(message: String, errorCode: Int): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }
}