# FlightSimulatorAndroidApp



## Introduction

This repository documents my last project in Adanced Programming 2 course at Bar-Ilan university.
In this project I've implemented an Android app that allows the user to remotely control an aircraft in FlightGear simulator.

![sshot1](https://user-images.githubusercontent.com/72878018/122671505-9f8a2b80-d1cf-11eb-8810-e9586d3ff038.png)



## System Requirements

1. Android device with Android 11.0 x86 OS (The program is likely to work on lower versions as well).
2. FlightGear simulator – Download here: https://www.flightgear.org/download/



## Model, View, ViewModel

The program is devided according to MVVM architecture:

1. **Model**:     Holds data (client) and logic, sends commands to the flight simulator.
2. **ViewModel**: Acts as the link between the Model and View.
3. **View**:      Responsible for the visualization and creating displays for the user



### Class diagram:

![FlightControlAppUML](https://user-images.githubusercontent.com/72878018/123062482-3dbb0300-d415-11eb-9e2e-fc50f40e43b2.png)



## Instructions for running the app using Android Studio

1. Install FlightGear on your machine.
2. Open FlightGear, go to "settings", scroll down to "Additional Settings", then write: "--telnet=socket,in,10,127.0.0.1,6400,tcp". If port 6400 is occupied, you can enter any available port x.
3. Download FlightSimulatorAndroidApp project.
4. Open Android Studio and start an AVD - recommend to start **Pixel XL API 30**.
5. Start FlightGear simulator.
6. Build and run the project from Android Studio.
7. Enter your machine's IP adrress and port 6400 (or port x you chose before).
8. Touch (click) connect.
9. You can now control the simulator.



## Instructions for running the app using your android device

1. Install FlightGear on your machine.
2. Open FlightGear, go to "settings", scroll down to "Additional Settings", then write: "--telnet=socket,in,10,127.0.0.1,6400,tcp". If port 6400 is occupied, you can enter any available port x.
3. From this repository, download the apk **flightcontrol-installation.apk** from installable-apk directory to your device.
4. Install the apk on your device. Note that you will probably need to approve the installation of apps from unauthorized sources as this is an educational program that is unapproved by Play Store.
5. Start FlightGear simulator.
6. Start Flight Simulator Control App on your device.
7. Enter your machine's IP adrress and port 6400 (or port x you chose before).
8. Touch connect.
9. You can now control the simulator.



## Presentation

Introduction video: https://youtu.be/GOK3DV1zgKs

Slides: [FlightControl App Presentation.pptx](https://github.com/shlomi1993/FlightSimulatorAndroidApp/files/6700190/FlightControl.App.Presentation.pptx)



## Guide for installation on Android Device

Download the apk an locate it (the apk in the screenshot is the flightcontrol-installation.apk before renaming).

![Screenshot_20210621-172617_My Files](https://user-images.githubusercontent.com/72878018/122782782-ac834980-d2b9-11eb-8611-71138ae60700.jpg)


Choose "Install" to start installation process.

![Screenshot_20210621-172622_Package installer](https://user-images.githubusercontent.com/72878018/122783172-fe2bd400-d2b9-11eb-9660-63961f6e956e.jpg)


This app is not recognized in Play Store so touch "INSTALL ANYWAY"

![Screenshot_20210621-172628_Google Play Store](https://user-images.githubusercontent.com/72878018/122783249-10a60d80-d2ba-11eb-9b1a-a0ee011de679.jpg)


Installing...

![Screenshot_20210621-172635_Package installer](https://user-images.githubusercontent.com/72878018/122783457-41864280-d2ba-11eb-8275-710f245cbce1.jpg)


Click "Open" to start the app.

![Screenshot_20210621-172647_Package installer](https://user-images.githubusercontent.com/72878018/122783493-4b0faa80-d2ba-11eb-9988-cb5bd3eac084.jpg)


It is possible that your device's Anti-Virus will ask to scan the app because it isn't recognized in Play Store.
You can choose "DON'T SEND", trust me :)

![Screenshot_20210621-172653_Google Play Store](https://user-images.githubusercontent.com/72878018/122783594-5e227a80-d2ba-11eb-9707-88c05c859db9.jpg)


Now you can start the app:

![Screenshot_20210621-172707_Flight Simulator Control App](https://user-images.githubusercontent.com/72878018/122783957-b5284f80-d2ba-11eb-960e-a9e79a76efe2.jpg)


Enter **valid** IP address and port number, and press connect.

![Screenshot_20210621-172718_Flight Simulator Control App](https://user-images.githubusercontent.com/72878018/122784022-c3766b80-d2ba-11eb-8bd7-2370ffe0ea8f.jpg)


You can now control the simulated aircraft - Have a nice flight!

![Screenshot_20210621-172738_Flight Simulator Control App](https://user-images.githubusercontent.com/72878018/122784087-d1c48780-d2ba-11eb-826f-388a9038e92a.jpg)



## IDE and tools

1. Android Studio
2. Notepad++
3. FlightGear Simulator
4. draw.io ( https://app.diagrams.net/ ) - for the UML
5. Samsung Galaxy S20 device.



## Important Notes

The program was tested on:
- Pixel XL API 30 emulator (on Android Studio IDE)
- Samsung galaxy S20 device (running android 11)
