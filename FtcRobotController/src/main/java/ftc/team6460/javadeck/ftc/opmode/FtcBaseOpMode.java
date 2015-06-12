package ftc.team6460.javadeck.ftc.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import ftc.team6460.javadeck.api.Maintainable;
import ftc.team6460.javadeck.api.Maintainer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hexafraction on 6/11/15.
 */
public abstract class FtcBaseOpMode extends OpMode implements Maintainer{
    private final Set<Maintainable> toMaintain = new HashSet<>();
    protected abstract void doActions();

    private FtcPeripheralsFactory mtrFactory = new FtcPeripheralsFactory(super.hardwareMap, this);

    @Override
    public void accept(Maintainable m) {
        toMaintain.add(m);
    }

    @Override
    public void start() {
        for(Maintainable m:toMaintain){
            m.setup();
        }
    }

    @Override
    public void loop() {
        for(Maintainable m:toMaintain){
            m.setup();
        }
    }

    @Override
    public void stop() {

    }
}
