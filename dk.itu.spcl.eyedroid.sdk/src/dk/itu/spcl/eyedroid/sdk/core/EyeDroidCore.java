package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
/**
 * EyeDroidCore class is the main implementation of the sdk.
 * The class provides a facade to control the execution of an {@link dk.itu.spcl.eyedroid.sdk.core.Computable} object
 * by an {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler}.
 */
public class EyeDroidCore {
    /**
     * Set the {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler}-{@link dk.itu.spcl.eyedroid.sdk.core.Computable} pair to
     * be executed by the core.
     * @param scheduler Defines how a computable is executed.
     * @param computable Defines the filters/pipes processing structure.
     */
    public void setup (Scheduler scheduler, Computable computable){

    }
    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the core to be processed.
     * @param bundle Wrapped object to be processed.
     */
    public void pushBundle (Bundle bundle){

    }
    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object processed by the core.
     * @return Return wrapped processing result.
     */
    public Bundle popBundle (){
        return new Bundle();
    }
}
