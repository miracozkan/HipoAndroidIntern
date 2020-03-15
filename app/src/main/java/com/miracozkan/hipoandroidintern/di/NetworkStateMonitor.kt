package com.miracozkan.hipoandroidintern.di

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 15.03.2020 - 10:55          │
//└─────────────────────────────┘

@Singleton
class NetworkStateMonitor @Inject constructor(private val cm: ConnectivityManager) :
    LiveData<NetworkState>() {

    private var hasNetworkChanged: Boolean = false

    private val networkStateObject = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network) {

            super.onLost(network)
            hasNetworkChanged = true
            postValue(NetworkState.CONNECTION_LOST)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            hasNetworkChanged = true
            postValue(NetworkState.DISCONNECTED)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            if (hasNetworkChanged) {
                postValue(NetworkState.CONNECTED)
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        cm.unregisterNetworkCallback(networkStateObject)
    }

    override fun onActive() {
        super.onActive()
        cm.registerNetworkCallback(networkRequestBuilder(), networkStateObject)
    }

    private fun networkRequestBuilder(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }
}

enum class NetworkState {
    CONNECTED,
    DISCONNECTED,
    CONNECTION_LOST
}