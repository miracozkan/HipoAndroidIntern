package com.miracozkan.hipoandroidintern.di.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:40          │
//└─────────────────────────────┘

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun isNetworkConnected(context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


}