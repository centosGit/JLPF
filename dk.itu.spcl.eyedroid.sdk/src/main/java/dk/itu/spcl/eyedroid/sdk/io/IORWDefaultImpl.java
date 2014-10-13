package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * This class is a convenient default implementation that acts both as a
 * {@link dk.itu.spcl.eyedroid.sdk.io.InputReader} and a {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}.
 * Other implementations could use different reader and writer objects.
 */
public class IORWDefaultImpl implements InputReader, OutputWriter {

    public final IOProtocolReader mReader;   //Reader implementation
    public final IOProtocolWriter mWriter;   //Writer implementation

    public IORWDefaultImpl(IOProtocolReader protocolReader, IOProtocolWriter protocolWriter) {
        mReader = protocolReader;
        mWriter = protocolWriter;
    }

    /**
     * Read bundle from input
     *
     * @return Wrapping bundle object
     */
    @Override
    public Bundle readInput() {
        return mReader.read();
    }

    /**
     * Write bundle to output
     *
     * @param bundle Wrapping bundle object
     */
    @Override
    public void writeOutput(Bundle bundle) {
        mWriter.write(bundle);
    }
}
