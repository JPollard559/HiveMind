# HiveMind — Safe Mecanum Drive Test

This repository includes `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/HiveMindDriveTest.java`.
The TeleOp appears on the Driver Station as **HiveMind Drive Test** once the Java
program has been built on and installed/loaded into the **Control Hub**.

> **Do not select the older "TeleOp" for your first test.** Its existing code
> starts an encoder drive command immediately after START. This new drive-test
> program never moves the robot unless LEFT BUMPER and a joystick are pressed.

## Control scheme

- **Hold LEFT BUMPER** to allow movement (release it to stop).
- **Left stick Y**: forward/backward.
- **Left stick X**: strafe left/right.
- **Right stick X**: turn left/right.
- **RIGHT BUMPER** held: maximum 50% motor power instead of 30%.
- **B** held: command zero motor power.

This test is **robot-centric** and does **not** require the IMU.

## Hardware names

The **active** robot configuration on the Driver Station must use these exact
motor names, matching the original `Hardware.java`:

- `frontLeft`
- `backLeft`
- `frontRight`
- `backRight`

In this repository, the original motor direction assumption is: **left motors
REVERSE**, right motors FORWARD. Verify each wheel on a stand before driving
on the ground. If your chassis differs, adjust the direction settings.

## Fastest installation: OnBot Java (no Android Studio required)

1. **While you still have internet**, open this file on GitHub and download
   `HiveMindDriveTest.java` (or copy its full contents to a local text file).
2. Power the Control Hub with its 12 V battery and switch, and connect the
   Driver Station to the Control Hub.
3. Connect a computer to the Control Hub's **Program & Manage Wi-Fi** network.
4. In a browser open **http://192.168.43.1:8080** and click **OnBot Java**.
5. Upload the downloaded `HiveMindDriveTest.java` to the default `teamcode`
   folder using the upload icon. Alternatively, click **+**, make a file
   named `HiveMindDriveTest.java`, choose a blank file, and paste the *entire*
   Java source into it.
6. Click the **Build** (wrench) button and look for **Build succeeded!**
7. On the Driver Station, select **TeleOp → HiveMind Drive Test**, then
   press **INIT** and **START**.

**Note:** Uploading a file to GitHub by itself does not put the code on the
physical hub. The OnBot Java Build step above is necessary.

## Alternative: Android Studio

1. Clone/download this repository and open it as the Android Studio project.
2. Connect your computer to the powered Control Hub using its **USB-C** port
   (not the mini-USB port).
3. Select the Control Hub as the target device in Android Studio. Run the FTC
   Robot Controller project/TeamCode configuration using the green **Run**
   button, allowing the build and install to complete.
4. When the Driver Station reconnects, choose
   **TeleOp → HiveMind Drive Test → INIT → START**.

If Android Studio reports project-configuration or SDK issues, use OnBot Java
for a first drive test before changing the repository's older build scripts.

## First-run checklist

- [ ] Battery charged; Control Hub powered; Driver Station linked.
- [ ] Active hardware configuration has the four **exact** motor names.
- [ ] Robot supported so all four wheels are **off the floor**.
- [ ] Gamepad connected and assigned as **gamepad 1**.
- [ ] Start **HiveMind Drive Test**, not the original `TeleOp`.
- [ ] Hold LEFT BUMPER and gently move left stick forward; all wheels respond.
- [ ] Check backward, strafing and rotation at the default 30% limit.
- [ ] If a direction is wrong, STOP and revise motor direction settings.
- [ ] Lower the robot and repeat carefully in a clear test area.

## Troubleshooting

- **OpMode missing:** Recheck build success and that you're viewing the
  **TeleOp**, not Autonomous, list.
- **Missing hardware name:** In the Driver Station's **Configure Robot** menu,
  check the active configuration against the four names above.
- **The robot won't move:** Hold LEFT BUMPER, release B, verify gamepad 1
  connection, and check the battery.
- **Moves incorrectly:** Stop; verify the exact order of physical motor
  connections, wheel orientations, and left/right motor directions.
- **Driver Station disconnected:** Check the Control Hub power and its wireless
  connection before troubleshooting the Java code.

Official guides:
- [OnBot Java: Creating and Running an OpMode](https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/onbot_java/creating_op_modes/Creating-and-Running-an-Op-Mode-%28OnBot-Java%29.html)
- [Android Studio: Creating and Running an OpMode](https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/android_studio/creating_op_modes/Creating-and-Running-an-Op-Mode-%28Android-Studio%29.html)
