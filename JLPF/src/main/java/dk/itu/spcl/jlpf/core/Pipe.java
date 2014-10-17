package dk.itu.spcl.jlpf.core;

import dk.itu.spcl.jlpf.common.Bundle;

import java.util.concurrent.BlockingQueue;

/**
 * Interface that represents a logical link between {@link dk.itu.spcl.jlpf.core.Filter} objects.
 * Provides input and output queues for {@link dk.itu.spcl.jlpf.common.Bundle} objects for two connected
 * filters.
 */

public abstract class Pipe {

    protected BlockingQueue<Bundle> mInternalQueue; //Internal bundle queue

    /**
     * Push a {@link dk.itu.spcl.jlpf.common.Bundle} object into the pipe.
     *
     * @param bundle Bundle object
     */
    public abstract void push(Bundle bundle);

    /**
     * Pop a {@link dk.itu.spcl.jlpf.common.Bundle} object from the pipe.
     *
     * @return Bundle object
     */
    public abstract Bundle pop();

    /**
     * Cleanup pipe queue content.
     */
    public void cleanup(){
        mInternalQueue.clear();
    }
}
