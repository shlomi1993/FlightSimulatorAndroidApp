# FlightSimulatorAndroidApp


## Introduction

This repository documents my last project in Adanced Programming 2 course at Bar-Ilan university.
In this project I've implemented an Android app that allows the user to remotely control an aircraft in FlightGear simulator.

![sshot1](https://user-images.githubusercontent.com/72878018/122671505-9f8a2b80-d1cf-11eb-8810-e9586d3ff038.png)


## System Requirements

1. Android device with Android 11.0 x86 OS – Tested on Pixel XL API 30 emulator.
2. FlightGear simulator – Download here: https://www.flightgear.org/download/


## Model, View, ViewModel

The program is devided according to MVVM architecture:

1. Model:     Holds data (client) and logic, sends commands to the flight simulator.
2. ViewModel: Acts as the link between the Model and View.
3. View:      Responsible for the visualization and creating displays for the user


### Class diagram:

![FlightControlAppUML2](https://user-images.githubusercontent.com/72878018/122671964-cea19c80-d1d1-11eb-8aad-c56aa9b0ddcc.png)


## Instructions

1. Install FlightGear on your machine.
2. Open FlightGear, go to "settings", scroll down to "Additional Settings", then write: "--telnet=socket,in,10,127.0.0.1,6400,tcp". If port 6400 is occupied, you can enter any available port x.
3. Download FlightSimulatorAndroidApp project.
4. Open Android Studio and start an AVD - recommend to start **Pixel XL API 30**.
5. Start FlightGear simulator.
6. Build and run the project from Android Studio.
7. Enter your machine's IP adrress and port 6400 (or port x you chose before).
8. Touch connect.
9. You can now control the simulator.


## Explanation Video

println("Not implemented")


## IDE and tools

1. Android Studio
2. Notepad++
3. FlightGear Simulator
4. draw.io ( https://app.diagrams.net/ ) - for the UML


## Presentation

[FlightControl App Presentation.pptx](https://github.com/shlomi1993/FlightSimulatorAndroidApp/files/6682147/FlightControl.App.Presentation.pptx)

