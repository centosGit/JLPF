package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Interface used to implement a specific reader. Implementations of this interface
 * do not perform the actual reading but the task is delegated to {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolReader}.
 * Clients of this class use the {@link InputReader#readInput()} to read the next {@link dk.itu.spcl.eyedroid.sdk.common.Bundle}.
 */
public interface InputReader {

    /**
     * Method used to read the next {@link dk.itu.spcl.eyedroid.sdk.common.Bundle}
     *
     * @return {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} the last bundle that was read.
     */
    public Bundle readInput();

    /**
     * Cleanup method
     */
    public void cleanup();
}
