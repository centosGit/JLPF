package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Interface that represents a logical link between {@link dk.itu.spcl.eyedroid.sdk.core.Filter} objects.
 * Provides input and output queues for {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} objects for two connected
 * filters.
 */
public class Pipe {

    private BlockingQueue<Bundle> mInternalQueue;

    public Pipe(){
        mInternalQueue = new LinkedBlockingQueue<Bundle>();
    }

    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the pipe.
     * @param bundle
     */
    public void push (Bundle bundle){
        try {
            mInternalQueue.put(bundle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object from the pipe.
     */
    public Bundle pop (){
        try {
            return mInternalQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Bundle();
        }
    }
    /**
     * Cleanup pipe content.
     */
    public void cleanup (){
        mInternalQueue.clear();
    }
}