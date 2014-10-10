package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
/**
 * Abstract class that defines how a {@link dk.itu.spcl.eyedroid.sdk.core.Computable} object must be executed.
 * Used by {@link dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore}.
 */
public abstract class Scheduler {
    /**
     * Run scheduler.
     * @param computable {@link dk.itu.spcl.eyedroid.sdk.core.Computable} to execute.
     */
    public void start (Computable computable){

    }
    /**
     * Stop scheduler.
     */
    public void stop (){

    }
    /**
     * Restart scheduler.
     */
    public void restart (){

    }
    /**
     * Cleanup scheduler content.
     */
    public void cleanup (){

    }
}
