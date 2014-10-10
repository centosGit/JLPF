package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
/**
 * Abstract class that defines overall behavior of a processing filter.
 * Each filter is connected to other filters by {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} objects.
 * A filter can be single or composed by other filters (composite pattern).
 */
public abstract class Filter {
    /**
     * Define a {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} object as input to the filter.
     * @param pipe Link representation between filters.
     */
    public void setInput (Pipe pipe){

    }
    /**
     * Define a {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} object as output of the filter.
     * @param pipe Link representation between filters.
     */
    public void setOutput (Pipe pipe){

    }
    /**
     * Push a processed {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object to the output queue.
     * @param bundle Processed wrapping object.
     */
    public void pushToOutput (Bundle bundle){

    }
    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object from the input queue.
     * @return Wrapping object to be processed.
     */
    public Bundle popFromInput (){

        return new Bundle();
    }
    /**
     *Start filter execution.
     */
    public void run (){

    }
    /**
     *Process a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object.
     * @param bundle
     */
    private void execute (Bundle bundle){

    }
}
