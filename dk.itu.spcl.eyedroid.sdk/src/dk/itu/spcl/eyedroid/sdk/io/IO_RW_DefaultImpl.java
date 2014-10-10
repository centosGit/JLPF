package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * This class is a convenient default implementation that acts both as a
 * {@link dk.itu.spcl.eyedroid.sdk.io.InputReader} and a {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}.
 *  Other implementations could use different reader and writer objects.
 */
public class IO_RW_DefaultImpl implements InputReader , OutputWriter{

    @Override
    public void setProtocolReader(IOProtocolReader protocolReader) {

    }

    @Override
    public Bundle readInput() {
        return null;
    }

    @Override
    public void setProtocolWriter(IOProtocolWriter protocolWriter) {

    }

    @Override
    public void writeOutput(Bundle bundle) {

    }
}
