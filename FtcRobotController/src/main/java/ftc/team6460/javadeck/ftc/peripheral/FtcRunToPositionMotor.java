package ftc.team6460.javadeck.ftc.peripheral;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.robocol.Telemetry;
import ftc.team6460.javadeck.api.SlewedDouble;
import ftc.team6460.javadeck.api.motion.SelfContainedServo;
import ftc.team6460.javadeck.api.peripheral.PeripheralCommunicationException;
import ftc.team6460.javadeck.api.peripheral.PeripheralInoperableException;
import ftc.team6460.javadeck.api.safety.SafetyGroup;


public class FtcRunToPositionMotor extends SelfContainedServo {

    private final DcMotor inner;
    private final Telemetry telemetry;
    private double valToWrite;
    private double power;
    private long shutdownUntil = 0;
    public FtcRunToPositionMotor(DcMotor inner, Telemetry telemetry) {
        this.inner = inner;
        this.telemetry = telemetry;

    }

    public FtcRunToPositionMotor(DcMotor inner) {
        this.inner = inner;
        this.telemetry = null;

    }
    @Override
    public void writeFast(SlewedDouble pos) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {
        valToWrite = pos.getValue();
        power = pos.getSlewRate();
    }

    @Override
    public void safetyShutdown(long nanos) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {
        shutdownUntil = System.currentTimeMillis() + (nanos/1000);
    }

    @Override
    public void addSafetyGroup(SafetyGroup safetyGroup) {
        // nothing needed here
    }
    @Override
    public void loop() {
        if(System.currentTimeMillis() > shutdownUntil)
            inner.setPowerFloat();
        else {
            // handle andymark neverest motors
            inner.setTargetPosition((int)(valToWrite*1440));
            inner.setPower(power);
        }
    }


    @Override
    public void setup() {
        inner.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
    }
}
