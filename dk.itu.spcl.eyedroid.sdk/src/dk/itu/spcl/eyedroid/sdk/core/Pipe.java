package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
/**
 * Interface that represents a logical link between {@link dk.itu.spcl.eyedroid.sdk.core.Filter} objects.
 * Provides input and output queues for {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} objects for two connected
 * filters.
 */
public interface Pipe {
    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the pipe.
     * @param bundle
     */
    public void push (Bundle bundle);
    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object from the pipe.
     */
    public Bundle pop ();
    /**
     * Cleanup pipe content.
     */
    public void cleanup ();
}