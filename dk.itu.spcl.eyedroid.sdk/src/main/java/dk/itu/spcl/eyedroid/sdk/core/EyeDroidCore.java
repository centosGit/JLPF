package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * EyeDroidCore class is the main implementation of the sdk.
 * The class provides a facade to control the execution of an {@link dk.itu.spcl.eyedroid.sdk.core.Computable} object
 * by an {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler}.
 */
public class EyeDroidCore {

    private Computable mComputable;     //Computable object used by the scheduler
    private Scheduler mScheduler;       //Scheduler executor

    /**
     * Set the {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler}-{@link dk.itu.spcl.eyedroid.sdk.core.Computable} pair to
     * be executed by the core.
     *
     * @param scheduler  Defines how a computable is executed
     * @param computable Defines the filters/pipes processing structure
     */
    public EyeDroidCore(Scheduler scheduler, Computable computable) {
        mComputable = computable;
        mScheduler = scheduler;
    }

    /**
     * Start {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler} object.
     */
    public void start() {
        mScheduler.innerStart();
    }

    /**
     * Stop {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler} object.
     */
    public void stop() {
        mScheduler.innerStop();
    }

    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the core to be processed.
     *
     * @param bundle Wrapped object to be processed.
     */
    public void pushBundle(Bundle bundle) {
        mComputable.pushToSource(bundle);
    }

    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object processed by the core.
     *
     * @return Return wrapped processing result.
     */
    public Bundle popBundle() {
        return mComputable.popFromSink();
    }
}
