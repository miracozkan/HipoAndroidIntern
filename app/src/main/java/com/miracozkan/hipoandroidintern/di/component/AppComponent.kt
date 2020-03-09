package com.miracozkan.hipoandroidintern.di.component

import android.app.Application
import com.miracozkan.hipoandroidintern.HipoAndroidInternApp
import com.miracozkan.hipoandroidintern.di.module.AppModule
import com.miracozkan.hipoandroidintern.di.module.FragmentModule
import com.miracozkan.hipoandroidintern.di.module.NetworkModule
import com.miracozkan.hipoandroidintern.di.module.RepositoryModule
import com.miracozkan.hipoandroidintern.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 08.03.2020 - 10:39          │
//└─────────────────────────────┘

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        FragmentModule::class
    ]
)

interface AppComponent : AndroidInjector<HipoAndroidInternApp> {

    override fun inject(instance: HipoAndroidInternApp?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

}