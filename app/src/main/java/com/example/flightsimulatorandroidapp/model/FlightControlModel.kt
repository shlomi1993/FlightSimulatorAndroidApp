package com.example.flightsimulatorandroidapp.model

import android.os.Build
import androidx.annotation.RequiresApi

class FlightControlModel @RequiresApi(Build.VERSION_CODES.M) constructor(private var client: Client) {

    fun setAileron(value: Float) {
        client.send("aileron", value.toString())
    }

    fun setElevator(value: Float) {
        client.send("elevator", value.toString())
    }

    fun setRudder(value: Float) {
        client.send("rudder", value.toString())
    }

    fun setThrottle(value: Float) {
        client.send("throttle", value.toString())
    }

}