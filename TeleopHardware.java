package shenanigans_code.shenanigans;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.hardware.ColorSensor;
/**
 * Created by alexi on 9/28/2017.
 */

public class TeleopHardware {
    /* Public OpMode members. */
    public DcMotor  leftDrive   = null;
    public DcMotor  rightDrive  = null;
    public DcMotor  pulleyMotor = null;
    public Servo    leftGripper    = null;
    public Servo    rightGripper   = null;
    public Servo    colorArm = null;
    public BNO055IMU imu = null;
    public ColorSensor colorSensor = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public TeleopHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        pulleyMotor = hwMap.get(DcMotor.class, "pulley_motor");
        leftGripper = hwMap.get(Servo.class, "left_gripper");
        rightGripper = hwMap.get(Servo.class, "right_gripper");
        colorArm = hwMap.get(Servo.class, "color_arm");
        imu = hwMap.get(BNO055IMU.class, "imu");

        //

        //leftArm    = hwMap.get(DcMotor.class, "left_arm");
        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        pulleyMotor.setDirection(DcMotor.Direction.FORWARD); //We still have to figure out which direction the motor will be facing
        leftGripper.setDirection(Servo.Direction.FORWARD);
        rightGripper.setDirection(Servo.Direction.REVERSE);
        colorArm.setDirection(Servo.Direction.FORWARD);
        //For the portion of above, we have to figure out what direction our motors and servos are facing.

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        pulleyMotor.setPower(0);
        leftGripper.setPosition(0);
        rightGripper.setPosition(0);
        colorArm.setPosition(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pulleyMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        // Define and initialize ALL installed servos.
        //leftClaw  = hwMap.get(Servo.class, "left_hand");
        //rightClaw = hwMap.get(Servo.class, "right_hand");
        //leftClaw.setPosition(MID_SERVO);
        //rightClaw.setPosition(MID_SERVO);


    }
}
