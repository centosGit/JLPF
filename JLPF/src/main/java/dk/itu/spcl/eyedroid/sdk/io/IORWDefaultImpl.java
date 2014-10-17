package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * This class is a convenient default implementation that acts both as a
 * {@link dk.itu.spcl.eyedroid.sdk.io.InputReader} and a {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}.
 * Other implementations could use different reader and writer objects.
 * The default implementation calls init and cleanup methods on IO protocols.
 */

public class IORWDefaultImpl implements InputReader, OutputWriter {

    private final IOProtocolReader protocolReader;   //Reader implementation
    private final IOProtocolWriter protocolWriter;   //Writer implementation

    public IORWDefaultImpl(IOProtocolReader protocolReader, IOProtocolWriter protocolWriter) {
        this.protocolReader = protocolReader;
        this.protocolWriter = protocolWriter;
    }

    /**
     * Read bundle from input
     *
     * @return Wrapping bundle object
     */
    @Override
    public Bundle readInput() {
        return protocolReader.read();
    }

    /**
     * Write bundle to output
     *
     * @param bundle Wrapping bundle object
     */
    @Override
    public void writeOutput(Bundle bundle) {
        protocolWriter.write(bundle);
    }

    /**
     * Cleanup method
     */
    public void cleanup(){
        protocolReader.cleanup();
        protocolWriter.cleanup();
    }
}
