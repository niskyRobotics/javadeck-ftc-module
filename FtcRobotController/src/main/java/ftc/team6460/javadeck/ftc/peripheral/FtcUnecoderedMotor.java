package ftc.team6460.javadeck.ftc.peripheral;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.robocol.Telemetry;
import ftc.team6460.javadeck.api.motion.EncoderedMotor;
import ftc.team6460.javadeck.api.motion.UnencoderedMotor;
import ftc.team6460.javadeck.api.peripheral.PeripheralCommunicationException;
import ftc.team6460.javadeck.api.peripheral.PeripheralInoperableException;
import ftc.team6460.javadeck.api.safety.SafetyGroup;

/**
 * FTC/Qualcomm platform unencodered motor.
 */
public class FtcUnecoderedMotor implements UnencoderedMotor {

    private final DcMotor inner;
    private final Telemetry telemetry;
    private double valToWrite;
    private long shutdownUntil = 0;

    public FtcUnecoderedMotor(DcMotor inner, Telemetry telemetry) {
        this.inner = inner;
        this.telemetry = telemetry;

    }

    public FtcUnecoderedMotor(DcMotor inner) {
        this.inner = inner;
        this.telemetry = null;

    }

    @Override
    public void write(Double input) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {
        writeFast(input);
    }

    @Override
    public synchronized void writeFast(Double input) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {
        this.valToWrite = input;
    }

    @Override
    public synchronized void loop() {
        if (System.currentTimeMillis() > shutdownUntil)
            inner.setPowerFloat();
        else
            inner.setPower(valToWrite);
    }

    @Override
    public void setup() {

        this.inner.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    @Override
    public synchronized void safetyShutdown(long nanos) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {
        shutdownUntil = System.currentTimeMillis() + (nanos / 1000);
    }

    @Override
    public void addSafetyGroup(SafetyGroup grp) {
        //pass
    }

}
