package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Interface used to implement a specific reader. Implementations of this interface
 * do not perform the actual reading but the task is delegated to
 * {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolReader}
 * . Clients of this class should first set a proper
 * {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolReader} instance
 * and then use the {@link InputReader#readInput()} to read the next
 * {@link dk.itu.spcl.eyedroid.sdk.common.Bundle}.
 */
public interface InputReader {

    /**
     * Method used to set an instance of a {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolReader}
     * @param protocolReader specific instance of {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolReader}
     * */
    public void setProtocolReader( IOProtocolReader protocolReader );

    /**
     *Method used to read the next {@link dk.itu.spcl.eyedroid.sdk.common.Bundle}
     * @return {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} the last bundle that was read.
    * */
    public Bundle readInput();
}
