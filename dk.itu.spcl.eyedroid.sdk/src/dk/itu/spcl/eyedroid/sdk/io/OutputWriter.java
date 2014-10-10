package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Interface used to implement a specific writer. Implementations of this interface
 * do not perform the actual writing but the task is delegated to
 * {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolWriter}
 * . Clients of this class should first set a proper
 * {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolWriter} instance
 * and then use the {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter#writeOutput(dk.itu.spcl.eyedroid.sdk.common.Bundle)}
 * to write the next processed
 * {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} to the output.
 */
public interface OutputWriter {

    /**
     * Method used to set an instance of a {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolWriter}
     *
     * @param protocolWriter specific instance of a {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolWriter}
     * */
    public void setProtocolWriter( IOProtocolWriter protocolWriter );

    /**
     *Method used to write the next processed {@link dk.itu.spcl.eyedroid.sdk.common.Bundle}
     *
     * @param {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} the next processed Bundle
     * */
    public void writeOutput( Bundle bundle );
}
