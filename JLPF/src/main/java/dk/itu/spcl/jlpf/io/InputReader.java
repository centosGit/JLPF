package dk.itu.spcl.jlpf.io;

import dk.itu.spcl.jlpf.common.Bundle;

import java.io.IOException;

/**
 * Interface used to implement a specific reader. Implementations of this interface
 * do not perform the actual reading but the task is delegated to {@link dk.itu.spcl.jlpf.io.IOProtocolReader}.
 * Clients of this class use the {@link InputReader#readInput()} to read the next {@link dk.itu.spcl.jlpf.common.Bundle}.
 */
public interface InputReader {

    /**
     * Method used to read the next {@link dk.itu.spcl.jlpf.common.Bundle}
     *
     * @return {@link dk.itu.spcl.jlpf.common.Bundle} the last bundle that was read.
     */
    public Bundle readInput() throws IOException;

    /**
     * Cleanup method
     */
    public void cleanup();
}
