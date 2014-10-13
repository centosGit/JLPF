package dk.itu.spcl.eyedroid.sdk.core;

/**
 * Abstract class that defines how a {@link dk.itu.spcl.eyedroid.sdk.core.Computable} object must be executed.
 * Used by {@link dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore}.
 */
public abstract class Scheduler {

    private Computable mComputable;
    private boolean isRunning;

    public Scheduler(Computable computable) {
        mComputable = computable;
    }

    /**
     * Get scheduler running state.
     *
     * @return Scheduler state
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Set scheduler running state
     *
     * @param is Set scheduler running state
     */
    private void setIsRunning(boolean is) {
        isRunning = is;
    }

    /**
     * Scheduler start implementation.
     */
    protected abstract void start();

    /**
     * Scheduler stop implementation.
     */
    protected abstract void stop();

    /**
     * Restart scheduler.
     */
    public void restart() {
        innerStop();
        cleanup();
        innerStart();
    }

    /**
     * Cleanup scheduler content.
     */
    public void cleanup() {
        mComputable.cleanup();
    }

    /**
     * Run scheduler.
     */
    protected void innerStart() {
        setIsRunning(true);
        start();
    }

    /**
     * Stop scheduler.
     */
    protected void innerStop() {
        setIsRunning(false);
        stop();
    }
}
