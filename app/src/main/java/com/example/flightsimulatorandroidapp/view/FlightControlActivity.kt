/**
 * Class:   FlightControlModel
 * Author:  Shlomi Ben-Shushan
 * Date:    19/6/2021
 */

package com.example.flightsimulatorandroidapp.view

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.flightsimulatorandroidapp.R
import com.example.flightsimulatorandroidapp.model.Client
import com.example.flightsimulatorandroidapp.model.FlightControlModel
import com.example.flightsimulatorandroidapp.viewModel.MainViewModel
import com.google.android.material.slider.Slider
import io.github.controlwear.virtual.joystick.android.JoystickView
import java.net.InetAddress
import kotlin.math.roundToInt

/**
 *  FlightControlActivity class is the View of the Flight Control component. It is connected the
 *  the flight_control_activity.xml file which describes the actual view with the joystick and
 *  other control components.
 */
class FlightControlActivity : AppCompatActivity() {

    // Client and ViewModel objects.
    private lateinit var client: Client
    private lateinit var mainViewModel: MainViewModel

    // Controls.
    private lateinit var joystick: JoystickView
    private lateinit var rudder: Slider
    private lateinit var throttle: Slider

    // View's "dashboard" components.
    private lateinit var aileronText: TextView
    private lateinit var elevatorText: TextView
    private lateinit var rudderText: TextView
    private lateinit var throttleText: TextView

    // Store ip and port for different uses.
    private var ip: String = "0.0.0.0"
    private var port: String = "0"

    /**
     *  This is the FlightControlActivity's constructor.
     *
     *  @param savedInstanceState - as part of Android architecture.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        // Call parent class onCreate() and set content view.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_control)

        // Client connect - at this point we can assume with high probability that the connection
        // will be successful. If not, this would be a problem we could not predict in MainActivity
        // so we will print a significant error.
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

        // Set initial dashboard's values.
        aileronText = findViewById<TextView>(R.id.aileronText)
        elevatorText = findViewById<TextView>(R.id.elevatorText)
        rudderText = findViewById<TextView>(R.id.rudderText)
        throttleText = findViewById<TextView>(R.id.throttleText)
        val initialAileron = "Aileron: 0.0"
        aileronText.text = initialAileron
        val initialElevator = "Elevator: 0.0"
        elevatorText.text = initialElevator
        val initialRudder = "Rudder: 0.0"
        rudderText.text = initialRudder
        val initialThrottle = "Throttle: 0.0"
        throttleText.text = initialThrottle


        // Create ViewModel (with Model connected to the client).
        mainViewModel = MainViewModel(FlightControlModel(client))

        // Create joystick.
        joystick = findViewById<JoystickView>(R.id.joystick)
        joystick.setOnMoveListener(JoystickView.OnMoveListener() { angle: Int, strength: Int ->
            val x = strength * kotlin.math.cos(Math.toRadians(angle * 1.0)).roundToInt() / 100.0
            val y = strength * kotlin.math.sin(Math.toRadians(angle * 1.0)).roundToInt() / 100.0
            mainViewModel.setAileron(x.toFloat())
            mainViewModel.setElevator(y.toFloat())
            val a = "Aileron: $x"
            aileronText.text = a
            val e = "Elevator: $y"
            elevatorText.text = e

        })

        // Create rudder.
        rudder = findViewById<Slider>(R.id.rudder)
        rudder.addOnChangeListener { rudder, value, fromUser ->
            mainViewModel.setRudder(value)
            val r = "Rudder: " + (value * 100).roundToInt() / 100.0
            rudderText.text = r
        }

        // Create throttle.
        throttle = findViewById<Slider>(R.id.throttle)
        throttle.addOnChangeListener { throttle, value, fromUser ->
            mainViewModel.setThrottle(value)
            val t = "Throttle: " + (value * 100).roundToInt() / 100.0
            throttleText.text = t
        }

    }

    /**
     *  This method disconnects the client when the user press the back button.
     */
    override fun onBackPressed() {
        client.disconnect()
        super.onBackPressed()
    }

    /**
     *  This method disconnects the client on the activity's destructor.
     */
    override fun onDestroy() {
        client.disconnect()
        super.onDestroy()
    }

}