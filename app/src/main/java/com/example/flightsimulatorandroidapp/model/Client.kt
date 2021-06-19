// Shlomi Ben-Shushan ID: 311408264

package com.example.flightsimulatorandroidapp.model

import android.os.AsyncTask
import java.io.IOException
import java.io.OutputStream
import java.io.Serializable
import java.lang.Exception
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class Client {

    private var requests: Map<String, String>
    private lateinit var socket: Socket
    private lateinit var output: OutputStream

    init {
        requests = mapOf(
            "aileron"   to  "/controls/flight/aileron",
            "elevator"  to  "/controls/flight/elevator",
            "rudder"    to  "/controls/flight/rudder",
            "throttle"  to  "/controls/engines/current-engine/throttle"
        )
    }

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

    fun send(parameter: String, value: String) {
        TaskRunner(this).execute(parameter, value)
    }

    fun disconnect() {
        try {
            output.close()
            socket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isConnected(): Boolean {
        if (socket.isConnected)
            return true
        return false
    }

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