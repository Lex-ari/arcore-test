package com.google.ar.core.examples

import android.content.Context
import com.google.ar.core.examples.api.N2YOApi
import com.google.ar.core.examples.api.PositionItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.lang.IllegalStateException


class SatelliteRepository{
    private val n2yoApi: N2YOApi
    //private val spacecraft: List<SpacecraftItem>
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.n2yo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            //.client(okHttpClient)
            .build()
        n2yoApi = retrofit.create()
        startIsolatedFetch()
    }
    companion object {
        private var INSTANCE: SatelliteRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SatelliteRepository()
            }
        }

        fun get(): SatelliteRepository {
            return INSTANCE ?: throw IllegalStateException("SatelliteRepository must be initialized")
        }
    }


    public lateinit var latestupdate: PositionItem

    suspend fun fetchSatellites() : List<PositionItem> = coroutineScope {
        n2yoApi.fetchSatellites().positions
    }

    suspend fun fetchSatellite() {
        latestupdate = n2yoApi.fetchSatellites().positions[0]
    }

    fun getLatestData() : PositionItem {
        return latestupdate
    }

    private var job: Job? = null
    private var running = false
    fun startIsolatedFetch() {
        running = true
        var job = CoroutineScope(Dispatchers.Default).launch {
            while (running) {
                fetchSatellite()
                delay(5000)
            }
        }
    }
    fun stopIsolatedFetch() {
        job?.cancel()
    }
}