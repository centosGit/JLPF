package dk.itu.spcl.jlpf.core.pipes;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.core.Pipe;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Timeout pipe implementation. Similar implementation to {@link dk.itu.spcl.jlpf.core.pipes.BlockingPipe}, but
 * a timeout for bundle consumption waiting time is set. Used when the processing is done in parallel but the number of
 * threads is less than the number of filters. Timeout implementation is needed in order to avoid starvation in the case
 * that the filters running first wait for bundles, but they have not been produced by predecessor filters.
 */

public class TimeOutPipe extends Pipe {

    private int mTimeOut;
    private TimeUnit mTimeUnit;
    public TimeOutPipe(int timeOut , TimeUnit timeUnit){
        mInternalQueue = new LinkedBlockingQueue<Bundle>(10);
        mTimeOut = timeOut;
        mTimeUnit = timeUnit;
    }

    /**
     * Push a {@link dk.itu.spcl.jlpf.common.Bundle} object into the pipe.
     *
     * @param bundle Bundle object
     */
    @Override
    public void push(Bundle bundle) {
        try {
            mInternalQueue.offer(bundle , mTimeOut, mTimeUnit);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Pop a {@link dk.itu.spcl.jlpf.common.Bundle} object from the pipe.
     *
     * @return Bundle object
     */
    @Override
    public Bundle pop() {
        try {
            return mInternalQueue.poll(mTimeOut , mTimeUnit);
        } catch (InterruptedException e) {
            return null;
        }
    }
}