package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Low-speed, robot-centric mecanum test for HiveMind.
 *
 * Driver Station: TeleOp -> "HiveMind Drive Test"
 *
 * CONTROLS
 * - Hold LEFT BUMPER to enable drive (dead-man switch).
 * - Left stick: forward/backward and strafe.
 * - Right stick X: turn.
 * - Hold RIGHT BUMPER for 50% power (normally 30%).
 * - Hold B to command an immediate motor stop.
 *
 * Motor names come from the existing Hardware.java file in this repository.
 * Do not use this file if the configured hardware names are different.
 * Confirm direction with the wheels raised before floor testing.
 */
@TeleOp(name = "HiveMind Drive Test", group = "HiveMind")
public class HiveMindDriveTest extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    private static final double NORMAL_SPEED = 0.30;
    private static final double BOOST_SPEED = 0.50;
    private static final double JOYSTICK_DEADBAND = 0.07;

    @Override
    public void runOpMode() {
        // Exact names used by HiveMind's existing Hardware.java.
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Match the original HiveMind hardware motor directions.
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        for (DcMotor motor : new DcMotor[]{frontLeft, backLeft, frontRight, backRight}) {
            motor.setPower(0);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            // RUN_WITHOUT_ENCODER avoids stale RUN_TO_POSITION settings.
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        telemetry.addLine("HiveMind Drive Test ready.");
        telemetry.addLine("Raise wheels for the first direction check.");
        telemetry.addLine("HOLD LEFT BUMPER + move sticks to drive.");
        telemetry.addLine("Hold RIGHT BUMPER for 50% speed; B = stop.");
        telemetry.update();

        waitForStart();

        try {
            while (opModeIsActive()) {
                // FTC gamepad Y points down, so negate it for forward.
                double forward = applyDeadband(-gamepad1.left_stick_y);
                double strafe = applyDeadband(gamepad1.left_stick_x);
                double turn = applyDeadband(gamepad1.right_stick_x);

                boolean driveEnabled = gamepad1.left_bumper && !gamepad1.b;
                double maxPower = gamepad1.right_bumper ? BOOST_SPEED : NORMAL_SPEED;

                if (!driveEnabled) {
                    stopDrive();
                } else {
                    double denominator = Math.max(
                            1.0, Math.abs(forward) + Math.abs(strafe) + Math.abs(turn));

                    double fl = (forward + strafe + turn) / denominator;
                    double bl = (forward - strafe + turn) / denominator;
                    double fr = (forward - strafe - turn) / denominator;
                    double br = (forward + strafe - turn) / denominator;

                    frontLeft.setPower(fl * maxPower);
                    backLeft.setPower(bl * maxPower);
                    frontRight.setPower(fr * maxPower);
                    backRight.setPower(br * maxPower);
                }

                telemetry.addData("Drive", driveEnabled ? "ENABLED" : "DISABLED");
                telemetry.addData("Max power", "%.0f%%", maxPower * 100.0);
                telemetry.addData("Forward / Strafe / Turn", "%.2f / %.2f / %.2f",
                        forward, strafe, turn);
                telemetry.addLine("Hold left bumper to drive; B stops.");
                telemetry.update();
                idle();
            }
        } finally {
            stopDrive();
        }
    }

    private static double applyDeadband(double input) {
        return Math.abs(input) < JOYSTICK_DEADBAND ? 0.0 : input;
    }

    private void stopDrive() {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
}
