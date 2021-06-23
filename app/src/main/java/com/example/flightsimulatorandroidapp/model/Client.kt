/**
 * Class:   Client
 * Author:  Shlomi Ben-Shushan
 * Date:    19/6/2021
 */

package com.example.flightsimulatorandroidapp.model

import java.io.IOException
import java.io.OutputStream
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Callable
import java.util.concurrent.Executors

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
    private var connected: Boolean = false
    private var throwed: Boolean = false

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
                if (socket.isConnected) {
                    output = socket.getOutputStream()
                    connected = true
                    throwed = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
        thread.join()
    }

    /**
     * This method send the server a new value to a certain parameter.
     * The method uses a java.util.concurrent library to do the job asynchronously.
     *
     * @param parameter - The parameter to change (aileron, throttle, ...).
     * @param value - The new value to set.
     */
    fun send(parameter: String, value: String) {
        if (connected) {
            Executors.newSingleThreadExecutor().submit(Callable {
                if (requests.containsKey(parameter)) {
                    val message = "set " + requests[parameter] + " " + value + " \r\n"
                    try {
                        output.write(message.toByteArray(Charsets.UTF_8))
                        output.flush()
                    } catch (e: IOException) {
                        connected = false
                        e.printStackTrace()
                    }
                }
            })
        } else if (!throwed) {
            throwed = true
            throw IOException()
        }
    }

    /**
     * This method disconnects the client from the server.
     */
    fun disconnect() {
        try {
            output.close()
            socket.close()
            connected = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This method returns true if the client is connected, and false otherwise.
     */
    fun isConnected(): Boolean {
        return socket.isConnected
    }

}