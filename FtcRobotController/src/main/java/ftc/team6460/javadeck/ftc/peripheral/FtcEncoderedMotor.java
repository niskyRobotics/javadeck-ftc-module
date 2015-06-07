package ftc.team6460.javadeck.ftc.peripheral;

import com.qualcomm.robotcore.hardware.DcMotor;
import ftc.team6460.javadeck.api.motion.EncoderedMotor;
import ftc.team6460.javadeck.api.peripheral.PeripheralCommunicationException;
import ftc.team6460.javadeck.api.peripheral.PeripheralInoperableException;
import ftc.team6460.javadeck.api.safety.SafetyGroup;

/**
 * Created by hexafraction on 6/6/15.
 */
public class FtcEncoderedMotor implements EncoderedMotor {

    private final DcMotor inner;

    public FtcEncoderedMotor(DcMotor inner) {
        this.inner = inner;
        inner.getEncoders();
    }

    @Override
    public void write(Double input) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {

    }

    @Override
    public void writeFast(Double input) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {

    }

    @Override
    public void loop() {

    }

    @Override
    public void setup() {

    }

    @Override
    public void safetyShutdown(long nanos) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {

    }

    @Override
    public void addSafetyGroup(SafetyGroup grp) {

    }

    @Override
    public void doWrite(double val) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {

    }

    @Override
    public boolean checkSafety() {
        // no safety check on this implementation
        return true;
    }

    @Override
    public Double read(Void params) throws InterruptedException, PeripheralCommunicationException, PeripheralInoperableException {
        return null;
    }

    @Override
    public void calibrate(Double val, Void params) throws InterruptedException, UnsupportedOperationException, PeripheralInoperableException, PeripheralCommunicationException {

    }
}
