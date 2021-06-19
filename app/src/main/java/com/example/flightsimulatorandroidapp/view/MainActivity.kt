package com.example.flightsimulatorandroidapp.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.flightsimulatorandroidapp.R
import com.example.flightsimulatorandroidapp.model.Client
import java.net.InetAddress


class MainActivity : AppCompatActivity() {

    private lateinit var client: Client

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connect = findViewById<Button>(R.id.connect)
        connect.setOnClickListener {

            val ip = findViewById<EditText>(R.id.input_ip).text.toString()
            val port = findViewById<EditText>(R.id.input_port).text.toString()

            try {
                client = Client()
                client.connect(InetAddress.getByName(ip), port.toInt())
                if (client.isConnected()) {
                    client.disconnect()
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

    private fun showConnectionErrorMessage(msg: String) {
        val AlertBuilder = AlertDialog.Builder(this)
        AlertBuilder.setTitle("Connection Error")
        AlertBuilder.setMessage(msg)
        AlertBuilder.setNeutralButton("OK") { dialog, which -> { } }
        AlertBuilder.show()
    }

}