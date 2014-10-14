package dk.itu.spcl.eyedroid.sdk.core;

/**
 * Abstract class that defines how a {@link dk.itu.spcl.eyedroid.sdk.core.Computable} object must be executed.
 * Used by {@link dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore}.
 */
public abstract class Scheduler {

    private boolean isRunning;
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
    protected abstract void start(Computable computable);

    /**
     * Scheduler stop implementation.
     */
    protected abstract void stop();

    /**
     * Restart scheduler.
     */
    public void restart(Computable computable) {
        innerStop();
        innerStart(computable);
    }
    /**
     * Run scheduler.
     */
    protected void innerStart(Computable computable) {
        setIsRunning(true);
        start(computable);
    }

    /**
     * Stop scheduler.
     */
    protected void innerStop() {
        setIsRunning(false);
        stop();
    }
}
