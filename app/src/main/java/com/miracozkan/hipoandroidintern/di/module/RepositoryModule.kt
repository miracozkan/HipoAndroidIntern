package com.miracozkan.hipoandroidintern.di.module

import com.miracozkan.hipoandroidintern.data.repository.MemberRepository
import com.miracozkan.hipoandroidintern.data.repository.MemberRepositoryImpl
import dagger.Binds
import dagger.Module


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:40          │
//└─────────────────────────────┘

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindMemberRepository(memberRepositoryImpl: MemberRepositoryImpl): MemberRepository

}