package dk.itu.spcl.jlpf.core.pipes;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.core.Pipe;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Blocking pipe implementation. Used when the processing is done by the same number of threads as filters.
 * Each filter runs in its own thread. By using blocking queues, threads can stand by while waiting for new bundles
 * to process and therefore reduce resource consumption.
 */

public class BlockingPipe extends Pipe {

    public BlockingPipe() {
        mInternalQueue = new LinkedBlockingQueue<Bundle>();
    }

    /**
     * Push a {@link dk.itu.spcl.jlpf.common.Bundle} object into the pipe.
     *
     * @param bundle Bundle object
     */
    @Override
    public void push(Bundle bundle) {
        try {
            mInternalQueue.put(bundle);
        } catch (InterruptedException e) {
            //TODO check dat
//            e.printStackTrace();
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
            return mInternalQueue.take();
        } catch (InterruptedException e) {
            //TODO check dat
            return null;
        }
    }
}