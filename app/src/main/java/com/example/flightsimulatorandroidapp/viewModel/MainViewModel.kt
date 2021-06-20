/**
 * Class:   MainViewModel
 * Author:  Shlomi Ben-Shushan
 * Date:    19/6/2021
 */

package com.example.flightsimulatorandroidapp.viewModel

import com.example.flightsimulatorandroidapp.model.FlightControlModel

/**
 *  MainViewModel is a class that responsible for the connection between the View and the Model so
 *  the program will be more scalable.
 */
class MainViewModel(flightControlModel: FlightControlModel) {

    // A ViewModel have its own Model object.
    private var vm_flightControlModel: FlightControlModel = flightControlModel

    // Initialize parameters to zero by default.
    private var vm_aileron: Float = 0F
    private var vm_elevator: Float = 0F
    private var vm_rudder: Float = 0F
    private var vm_throttle: Float = 0F

    /**
     *  Aileron getter.
     *
     *  @return - The value in vm_aileron.
     */
    fun getAileron(): Float { return vm_aileron }

    /**
     *  Aileron setter - uses Model's setAileron() method.
     *
     *  @param value - The value to be set for aileron.
     */
    fun setAileron(value: Float) {
        if (vm_aileron != value)  {
            vm_aileron = value
            vm_flightControlModel.setAileron(value)
        }
    }

    /**
     *  Elevator getter.
     *
     *  @return - The value in vm_elevator.
     */
    fun getElevator(): Float { return vm_elevator }

    /**
     *  Elevator setter - uses Model's setElevator() method.
     *
     *  @param value - The value to be set for elevator.
     */
    fun setElevator(value: Float) {
        if (vm_elevator != value)  {
            vm_elevator = value
            vm_flightControlModel.setElevator(value)
        }
    }

    /**
     *  Rudder getter.
     *
     *  @return - The value in vm_rudder.
     */
    fun getRudder(): Float { return vm_rudder }

    /**
     *  Rudder setter - uses Model's setRudder() method.
     *
     *  @param value - The value to be set for rudder.
     */
    fun setRudder(value: Float) {
        if (vm_rudder != value)  {
            vm_rudder = value
            vm_flightControlModel.setRudder(value)
        }
    }

    /**
     *  Throttle getter.
     *
     *  @return - The value in vm_rudder.
     */
    fun getThrottle(): Float { return vm_rudder }

    /**
     *  Throttle setter - uses Model's setThrottle() method.
     *
     *  @param value - The value to be set for throttle.
     */
    fun setThrottle(value: Float) {
        if (vm_throttle != value)  {
            vm_throttle = value
            vm_flightControlModel.setThrottle(value)
        }
    }

}