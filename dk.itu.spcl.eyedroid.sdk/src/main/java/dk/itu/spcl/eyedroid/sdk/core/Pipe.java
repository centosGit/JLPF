package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

import java.util.concurrent.BlockingQueue;

/**
 * Created by centos on 10/14/14.
 */
/**
 * Interface that represents a logical link between {@link dk.itu.spcl.eyedroid.sdk.core.Filter} objects.
 * Provides input and output queues for {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} objects for two connected
 * filters.
 */
public abstract class Pipe {

    protected BlockingQueue<Bundle> mInternalQueue;


    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the pipe.
     *
     * @param bundle
     */
    public abstract void push(Bundle bundle);
    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object from the pipe.
     */
    public abstract Bundle pop();

    /**
     * Cleanup pipe queue content.
     */
    public void cleanup(){
        mInternalQueue.clear();
    }
}
