package com.google.ar.core.examples.kotlin.helloar

import android.app.Application
import android.content.Intent

class HelloApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        PhoneTelemetry.initialize(this)
        MathSatellite.initialize()
    }
}