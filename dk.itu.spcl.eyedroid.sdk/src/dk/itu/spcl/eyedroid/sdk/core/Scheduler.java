package dk.itu.spcl.eyedroid.sdk.core;

/**
 * Abstract class that defines how a {@link dk.itu.spcl.eyedroid.sdk.core.Computable} object must be executed.
 * Used by {@link dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore}.
 */
public abstract class Scheduler {

   private Computable mComputable;


    private boolean isRunning;

    public Scheduler( Computable computable ){
        mComputable = computable;
    }
    /**
     * Run scheduler.
     *
     */
    public void innerStart (){
        setIsRunning(true);
        start();
    }

    protected abstract void start();
    /**
     * Stop scheduler.
     */
    public void innerStop(){
        setIsRunning(false);
        stop();
    }
    public abstract void stop ();
    /**
     * Restart scheduler.
     */
    public void restart (){
        stop();
        cleanup();
        start();
    }
    /**
     * Cleanup scheduler content.
     */
    public void cleanup (){
        mComputable.cleanUp();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean is){
        isRunning = is;
    }

}
