package dk.itu.spcl.jlpf.core;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.core.pipes.BlockingPipe;

/**
 * Abstract class that defines overall behavior of a processing filter.
 * Each filter is connected to other filters by {@link dk.itu.spcl.jlpf.core.pipes.BlockingPipe} objects.
 * A filter can be single or composed by other filters (composite pattern).
 */

public abstract class Filter implements Runnable {

    private String mFilterName;    //Filter name
    private boolean mHasStarted;   //Filter state
    private Pipe mInputPipe;       //Filter input pipe
    private Pipe mOutputPipe;      //Filter output pipe
    private double mAvgExecutionTime;
    private int mExecutionCounter;

    /**
     * Get filter id.
     *
     * @return Object hash code
     */
    public int getFilterId() {
        return this.hashCode();
    }

    /**
     * Get filter name for logging purpose.
     *
     * @return Filter name
     */
    public String getFilterName() {
        return mFilterName;
    }

    /**
     * Set filter name for logging purpose.
     *
     * @param mFilterName Filter name
     */
    public void setFilterName(String mFilterName) {
        if (!hasStarted())
            this.mFilterName = mFilterName;
    }

    /**
     * Get filter execution state.
     *
     * @return Filter state
     */
    public boolean hasStarted() {
        return mHasStarted;
    }

    /**
     * Set filter execution state as started
     */
    protected void setStarted() {
        mHasStarted = true;
    }

    /**
     * Get filter input pipe (entry point).
     *
     * @return Input pipe
     */
    public Pipe getInputPipe() {
        return mInputPipe;
    }

    /**
     * Define a {@link BlockingPipe} object as input to the filter.
     *
     * @param pipe Link representation between filters
     */
    public void setInput(Pipe pipe) {
        if (!hasStarted())
            mInputPipe = pipe;
    }

    /**
     * Define a {@link BlockingPipe} object as output of the filter.
     *
     * @param pipe Link representation between filters
     */
    public void setOutput(Pipe pipe) {
        if (!hasStarted())
            mOutputPipe = pipe;
    }

    /**
     * Pop a {@link dk.itu.spcl.jlpf.common.Bundle} object from the input queue.
     *
     * @return Wrapping object to be processed
     */
    protected Bundle popFromInput() {
        return mInputPipe.pop();
    }

    /**
     * Push a processed {@link dk.itu.spcl.jlpf.common.Bundle} object to the output queue.
     *
     * @param bundle Processed wrapping object
     */
    protected void pushToOutput(Bundle bundle) {
        if (bundle != null)
            mOutputPipe.push(bundle);
    }

    /**
     * Process a {@link dk.itu.spcl.jlpf.common.Bundle} object.
     */
    @Override
    public void run() {
        Bundle outputBundle;
        Bundle inputBundle = popFromInput();

        if( inputBundle == null )
            return;

            outputBundle = innerExecute(inputBundle);

        if (outputBundle != null)
            pushToOutput(outputBundle);
    }

    /**
     * Filter execution implementation.
     *
     * @param bundle Wrapping object
     * @return Processed wrapping object
     */
    protected abstract Bundle execute(Bundle bundle);  //Internal usage

    protected Bundle innerExecute(Bundle bundle){
        setStarted();
        long startTime = startTimer();
        Bundle newBundle = execute(bundle);
        stopTimer(startTime);
        return newBundle;
    }

    //TODO check if the timing needs synchronization
    private long startTimer(){
        mExecutionCounter++;
        return System.nanoTime();
    }

    private void stopTimer(long startTime){
        double time = (System.nanoTime() - startTime) / 1000000; //to get ms
        mAvgExecutionTime += time;
    }

    public int getExecutionCounter(){
        return mExecutionCounter;
    }

    public double getAvgExecutionTime(){
        return mAvgExecutionTime / mExecutionCounter;
    }
}
