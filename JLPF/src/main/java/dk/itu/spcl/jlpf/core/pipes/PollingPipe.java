package dk.itu.spcl.jlpf.core.pipes;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.core.Pipe;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Polling pipe implementation. Used when the processing is done sequentially (all the filters are executed
 * by the same thread). No need of blocking is needed because a filter is executed always after a bundle is
 * produced by the previous one.
 */

public class PollingPipe extends Pipe {

    public PollingPipe(int capacity ) {
        super(capacity);
        mInternalQueue = new LinkedBlockingDeque<Bundle>(10);
    }

    /**
     * Push a {@link dk.itu.spcl.jlpf.common.Bundle} object into the pipe.
     *
     * @param bundle Bundle object
     */
    @Override
    public void push(Bundle bundle) {
        mInternalQueue.offer(bundle);
    }

    /**
     * Pop a {@link dk.itu.spcl.jlpf.common.Bundle} object from the pipe.
     *
     * @return Bundle object
     */
    @Override
    public Bundle pop() {
        return mInternalQueue.poll();
    }
}