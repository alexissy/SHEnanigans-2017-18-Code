package shenanigans_code.shenanigans;

/**
 * Created by alexi on 11/4/2017.
 */
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Test: Teleop Servo", group= "Test")
public class TestTeleopServo extends OpMode{
    TeleopHardware robot       = new TeleopHardware(); // use the class created to define a Pushbot's hardware
    // could also use HardwarePushbotMatrix class.
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        robot.colorArm.setPosition(45);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        double leftG;
        double rightG;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)

        leftG = gamepad2.right_stick_y;
        rightG = gamepad2.right_stick_y;

        telemetry.addData("%d", leftG);
        telemetry.addData("%d", rightG);


        robot.leftGripper.setPosition(leftG);
        robot.rightGripper.setPosition(rightG);

        // Use gamepad left & right Bumpers to open and close the claw
        if (gamepad1.right_bumper)
            clawOffset += CLAW_SPEED;
        else if (gamepad1.left_bumper)
            clawOffset -= CLAW_SPEED;

        // Move both servos to new position.  Assume servos are mirror image of each other.
        //clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        //robot.leftGripper.setPosition(robot.MID_SERVO + clawOffset);
        //robot.rightGripper.setPosition(robot.MID_SERVO - clawOffset);

        // Use gamepad buttons to move the arm up (Y) and down (A)
        //if (gamepad2.a) {
            //robot.leftGripper.setPosition(0.0);
            //robot.rightGripper.setPosition(0.0);
        //}
        //else if (gamepad2.b) {
            //robot.leftGripper.setPosition(90);
            //robot.rightGripper.setPosition(-90);
        //}
        //else if (gamepad2.y){
            //robot.leftGripper.setPosition(45);
            //robot.rightGripper.setPosition(-45);
        //}
        //else if (gamepad2.x){
            //robot.colorArm.setPosition(0);
        //}


        // Send telemetry message to signify robot running;
        telemetry.addData("claw",  "Offset = %.2f", clawOffset);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}


