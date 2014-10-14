package dk.itu.spcl.eyedroid.sdk.core.pipes;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.Pipe;

import java.util.concurrent.LinkedBlockingQueue;


public class BlockingPipe extends Pipe {


    public BlockingPipe() {
        mInternalQueue = new LinkedBlockingQueue<Bundle>();
    }

    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the pipe.
     *
     * @param bundle
     */
    @Override
    public void push(Bundle bundle) {
        try {
            mInternalQueue.put(bundle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object from the pipe.
     */
    @Override
    public Bundle pop() {
        try {
            return mInternalQueue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }
}