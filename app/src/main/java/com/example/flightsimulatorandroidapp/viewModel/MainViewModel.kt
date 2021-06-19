package com.example.flightsimulatorandroidapp.viewModel

import com.example.flightsimulatorandroidapp.model.FlightControlModel

class MainViewModel(flightControlModel: FlightControlModel) {

    private var vm_flightControlModel: FlightControlModel = flightControlModel

    private var vm_aileron: Float = 0F
    private var vm_elevator: Float = 0F
    private var vm_rudder: Float = 0F
    private var vm_throttle: Float = 0F

    fun getAileron(): Float { return vm_aileron }
    fun setAileron(value: Float) {
        if (vm_aileron != value)  {
            vm_aileron = value
            vm_flightControlModel.setAileron(value)
        }
    }

    fun getElevator(): Float { return vm_elevator }
    fun setElevator(value: Float) {
        if (vm_elevator != value)  {
            vm_elevator = value
            vm_flightControlModel.setElevator(value)
        }
    }

    fun getRudder(): Float { return vm_rudder }
    fun setRudder(value: Float) {
        if (vm_rudder != value)  {
            vm_rudder = value
            vm_flightControlModel.setRudder(value)
        }
    }

    fun getThrottle(): Float { return vm_rudder }
    fun setThrottle(value: Float) {
        if (vm_throttle != value)  {
            vm_throttle = value
            vm_flightControlModel.setThrottle(value)
        }
    }

}