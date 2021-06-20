/**
 * Class:   FlightControlModel
 * Author:  Shlomi Ben-Shushan
 * Date:    19/6/2021
 */

package com.example.flightsimulatorandroidapp.model

import android.os.Build
import androidx.annotation.RequiresApi

/**
 *  FlightControlModel is the class that responsible for sending new flight control commands to the
 *  server using a Client object.
 */
class FlightControlModel @RequiresApi(Build.VERSION_CODES.M) constructor(private var client: Client) {

    /**
     * This method sends the server a new value to be set for aileron.
     *
     * @param value - the new value.
     */
    fun setAileron(value: Float) {
        client.send("aileron", value.toString())
    }

    /**
     * This method sends the server a new value to be set for elevator.
     *
     * @param value - the new value.
     */
    fun setElevator(value: Float) {
        client.send("elevator", value.toString())
    }

    /**
     * This method sends the server a new value to be set for rudder.
     *
     * @param value - the new value.
     */
    fun setRudder(value: Float) {
        client.send("rudder", value.toString())
    }

    /**
     * This method sends the server a new value to be set for throttle.
     *
     * @param value - the new value.
     */
    fun setThrottle(value: Float) {
        client.send("throttle", value.toString())
    }

}