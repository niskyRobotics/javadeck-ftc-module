package ftc.team6460.javadeck.ftc.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import ftc.team6460.javadeck.api.Maintainable;
import ftc.team6460.javadeck.api.Maintainer;
import ftc.team6460.javadeck.ftc.peripheral.FtcPeripheralsFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hexafraction on 6/11/15.
 */
public abstract class FtcBaseOpMode extends OpMode implements Maintainer {
    private final Set<Maintainable> toMaintain = new HashSet<>();

    /**
     * Needs to catch and handle InterruptedException.
     */
    protected abstract void doActions();

    protected volatile boolean run = false;

    protected FtcPeripheralsFactory peripheralFactory = new FtcPeripheralsFactory(super.hardwareMap, this);
    private volatile Thread runThread;

    @Override
    public void accept(Maintainable m) {
        toMaintain.add(m);
    }


    //synchronization not needed; start(), loop(), and stop() are called from same thread (guarantee by com.qualcomm API)
    @Override
    public void start() {
        for (Maintainable m : toMaintain) {
            m.setup();
        }
        run = true;
        runThread = new Thread(() -> {
            doActions();
        }, "FtcOpModeAsyncThread@" + this.hashCode());
        runThread.start();

    }

    @Override
    public void loop() {
        for (Maintainable m : toMaintain) {
            m.setup();
        }
    }

    @Override
    public void stop() {
        run=false;
        runThread.interrupt();
    }
}
