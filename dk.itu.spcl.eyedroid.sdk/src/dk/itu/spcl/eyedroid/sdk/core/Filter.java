package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Abstract class that defines overall behavior of a processing filter.
 * Each filter is connected to other filters by {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} objects.
 * A filter can be single or composed by other filters (composite pattern).
 */
public abstract class Filter implements Runnable {

    private Pipe mInputPipe;
    private Pipe mOutputPipe;

    private String mFilterName;

    private boolean mHasStarted;

    public String getFilterName() {
        return mFilterName;
    }

    public void setFilterName(String mFilterName) {
        if (!hasStarted())
            this.mFilterName = mFilterName;
    }

    public int getFilterId() {
        return this.hashCode();
    }

    public boolean hasStarted() {
        return mHasStarted;
    }

    protected void setStarted() {
        mHasStarted = true;
    }


    /**
     * Define a {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} object as input to the filter.
     *
     * @param pipe Link representation between filters.
     */
    public void setInput(Pipe pipe) {
        if (!hasStarted())
            mInputPipe = pipe;
    }

    /**
     * Define a {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} object as output of the filter.
     *
     * @param pipe Link representation between filters.
     */
    public void setOutput(Pipe pipe) {
        if (!hasStarted())
            mOutputPipe = pipe;
    }

    public Pipe getInputPipe(){
        return mInputPipe;
    }

    /**
     * Push a processed {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object to the output queue.
     *
     * @param bundle Processed wrapping object.
     */
    protected void pushToOutput(Bundle bundle) {
            if (bundle != null)
                mOutputPipe.push(bundle);
    }

    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object from the input queue.
     *
     * @return Wrapping object to be processed.
     */
    protected Bundle popFromInput() {
        return mInputPipe.pop();
    }


    /**
     * Process a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object.
     *
     * @param bundle
     */
    protected abstract Bundle execute(Bundle bundle);


    @Override
    public void run() {

        Bundle outputBundle = null;
        Bundle inputBundle = popFromInput();
        if (inputBundle != null)
            outputBundle = execute(inputBundle);

        if( outputBundle != null )
            pushToOutput(outputBundle);

    }
}
