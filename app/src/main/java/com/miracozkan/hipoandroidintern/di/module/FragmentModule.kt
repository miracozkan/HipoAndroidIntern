package com.miracozkan.hipoandroidintern.di.module

import com.miracozkan.hipoandroidintern.ui.member_search.MemberSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:40          │
//└─────────────────────────────┘

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesMemberSearchFragment(): MemberSearchFragment
}