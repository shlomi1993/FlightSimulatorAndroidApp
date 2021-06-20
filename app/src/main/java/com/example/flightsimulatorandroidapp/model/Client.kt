/**
 * Class:   Client
 * Author:  Shlomi Ben-Shushan
 * Date:    19/6/2021
 */

package com.example.flightsimulatorandroidapp.model

import android.os.AsyncTask
import java.io.IOException
import java.io.OutputStream
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

/**
 *  Client is a class that responsible for communicating with FlightGear server and sending
 *  flight control command to it.
 */
class Client {

    // A client have a set of commands stored in a map, a Socket and an OutputStream variables.
    private var requests: Map<String, String> = mapOf(
        "aileron"   to  "/controls/flight/aileron",
        "elevator"  to  "/controls/flight/elevator",
        "rudder"    to  "/controls/flight/rudder",
        "throttle"  to  "/controls/engines/current-engine/throttle"
    )
    private lateinit var socket: Socket
    private lateinit var output: OutputStream

    /**
     * This method connects the client to a server by given IP and port.
     * The method does each connection in a different thread.
     * Note that the client tries to connect only for 5 seconds to avoid program from being frozen.
     *
     * @param ip - IP address from type InetAddress
     * @param port - An integer that represents a port number.
     */
    fun connect(ip: InetAddress, port: Int) {
        val thread = Thread(Runnable {
            try {
                socket = Socket()
                socket.connect(InetSocketAddress(ip, port), 5000)
                if (socket.isConnected) output = socket.getOutputStream()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
        thread.join()
    }

    /**
     * This method send the server a new value to a certain parameter.
     * The method actually uses a TaskRunner companion class to do the job.
     *
     * @param parameter - The parameter to change (aileron, throttle, ...).
     * @param value - The new value to set.
     */
    fun send(parameter: String, value: String) {
        TaskRunner(this).execute(parameter, value)
    }

    /**
     * This method disconnects the client from the server.
     */
    fun disconnect() {
        try {
            output.close()
            socket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This method returns true if the client is connected, and false otherwise.
     */
    fun isConnected(): Boolean {
        if (socket.isConnected)
            return true
        return false
    }

    /**
     * This class helps the send() method to send the command to the server in the background.
     */
    companion object {
        private class TaskRunner(private val client: Client) : AsyncTask<String, Unit, Unit>() {
            override fun doInBackground(vararg args: String?) {
                if (!client.requests.containsKey(args[0])) return
                val message = ("set " + client.requests[args[0]] + " " + args[1] + " \r\n").toByteArray(Charsets.UTF_8)
                try {
                    client.output.write(message)
                    client.output.flush()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}