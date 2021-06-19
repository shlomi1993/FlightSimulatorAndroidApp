package com.example.flightsimulatorandroidapp.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.flightsimulatorandroidapp.R
import com.example.flightsimulatorandroidapp.model.Client
import com.example.flightsimulatorandroidapp.model.FlightControlModel
import com.example.flightsimulatorandroidapp.viewModel.MainViewModel
import com.google.android.material.slider.Slider
import io.github.controlwear.virtual.joystick.android.JoystickView
import org.w3c.dom.Text
import java.net.InetAddress
import kotlin.math.roundToInt

class FlightControlActivity : AppCompatActivity() {

    private lateinit var client: Client
    private lateinit var mainViewModel: MainViewModel

    private lateinit var joystick: JoystickView
    private lateinit var rudder: Slider
    private lateinit var throttle: Slider

    private lateinit var aileronText: TextView
    private lateinit var elevatorText: TextView
    private lateinit var rudderText: TextView
    private lateinit var throttleText: TextView

    private var ip: String = "0.0.0.0"
    private var port: String = "0"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        // Set content view.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_control)

        // Set client.
        val intent = intent
        try {
            ip = intent.getStringExtra("ip").toString()
            port = intent.getStringExtra("port").toString()
            client = Client()
            client.connect(InetAddress.getByName(ip), port.toInt())
            val msg = "Connected to:\n    IP $ip\n    Port $port"
            findViewById<TextView>(R.id.connectionMessage).setText(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        aileronText = findViewById<TextView>(R.id.aileronText)
        elevatorText = findViewById<TextView>(R.id.elevatorText)
        rudderText = findViewById<TextView>(R.id.rudderText)
        throttleText = findViewById<TextView>(R.id.throttleText)
        val initial_a = "Aileron: 0.0"
        aileronText.setText(initial_a)
        val initial_e = "Elevator: 0.0"
        elevatorText.setText(initial_e)
        val initial_r = "Rudder: 0.0"
        rudderText.setText(initial_r)
        val initial_t = "Throttle: 0.0"
        throttleText.setText(initial_t)


        // Create ViewModel (with Model connected to the client).
        mainViewModel = MainViewModel(FlightControlModel(client))

        // Create joystick.
        joystick = findViewById<JoystickView>(R.id.joystick)
        joystick.setOnMoveListener(JoystickView.OnMoveListener() { angle: Int, strength: Int ->

            val x = strength * kotlin.math.cos(Math.toRadians(angle * 1.0)).roundToInt() / 100.0
            val y = strength * kotlin.math.sin(Math.toRadians(angle * 1.0)).roundToInt() / 100.0

            mainViewModel.setAileron(x.toFloat())
            mainViewModel.setElevator(y.toFloat())

            val a = "Aileron: " + x
            aileronText.setText(a)

            val e = "Elevator: " + y
            elevatorText.setText(e)

        })

        // Create rudder.
        rudder = findViewById<Slider>(R.id.rudder)
        rudder.addOnChangeListener { rudder, value, fromUser ->
            mainViewModel.setRudder(value)
            val r = "Rudder: " + (value * 100).roundToInt() / 100.0
            rudderText.setText(r)
        }

        // Create throttle.
        throttle = findViewById<Slider>(R.id.throttle)
        throttle.addOnChangeListener { throttle, value, fromUser ->
            mainViewModel.setThrottle(value)
            val t = "Throttle: " + (value * 100).roundToInt() / 100.0
            throttleText.setText(t)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        client.disconnect()
    }

    override fun onDestroy() {
        client.disconnect()
        super.onDestroy()
    }

}