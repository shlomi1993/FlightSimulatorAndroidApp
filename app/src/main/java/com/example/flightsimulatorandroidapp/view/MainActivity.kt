/**
 * Class:   MainActivity
 * Author:  Shlomi Ben-Shushan
 * Date:    19/6/2021
 */

package com.example.flightsimulatorandroidapp.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.flightsimulatorandroidapp.R
import com.example.flightsimulatorandroidapp.model.Client
import java.net.InetAddress

/**
 *  MainActivity class is the main View and the entry point of the program. It asks the user for
 *  IP address and port number in order to connect the app to the simulator and creates a
 *  FlightControlActivity object.
 */
class MainActivity : AppCompatActivity() {

    // dummyClient created to check for connection problems before connecting the "real" client.
    private lateinit var dummyClient: Client

    // View's components.
    private lateinit var ip: EditText
    private lateinit var port: EditText
    private lateinit var connect: Button

    // TextWatcher -- to identify input changes.
    private lateinit var textWatcher: TextWatcher

    /**
     *  This is the MainActivity's constructor.
     *
     *  @param savedInstanceState - as part of Android architecture.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        // Call parent's onCreate(), set content view and View's components.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ip = findViewById(R.id.input_ip)
        port = findViewById(R.id.input_port)
        connect = findViewById(R.id.connect)

        // Create TextWatcher object to allow clicking on "connect" only if IP and port inserted.
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val ipInput = ip.text.toString().trim()
                val portInput = port.text.toString().trim()
                connect.isEnabled = ipInput.isNotEmpty() && portInput.isNotEmpty()
            }
            override fun afterTextChanged(s: Editable?) { }
        }

        // Set listeners.
        ip.addTextChangedListener(textWatcher)
        port.addTextChangedListener(textWatcher)
        connect.setOnClickListener {
            val ip = findViewById<EditText>(R.id.input_ip).text.toString()
            val port = findViewById<EditText>(R.id.input_port).text.toString()
            try {
                dummyClient = Client()
                dummyClient.connect(InetAddress.getByName(ip), port.toInt())
                if (dummyClient.isConnected()) {
                    dummyClient.disconnect()
                    val intent = Intent(this, FlightControlActivity::class.java)
                    intent.putExtra("ip", ip)
                    intent.putExtra("port", port)
                    startActivity(intent)
                } else {
                    showConnectionErrorMessage("Destination unreachable.")
                }
            } catch (e: Exception) {
                showConnectionErrorMessage("Invalid IP or Port.")
            }
        }

    }

    /**
     *  This method pops up a connection error message to the screen.
     *
     *  @param msg - a message that provides information about the error.
     */
    private fun showConnectionErrorMessage(msg: String) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Connection Error")
        alertBuilder.setMessage(msg)
        alertBuilder.setNeutralButton("OK") { _, _ -> { } }
        alertBuilder.show()
    }

}