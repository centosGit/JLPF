package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Base interface for every object that wants to act as a {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}
 * This class should be passed to a {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter} instance and used by the
 * {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}.
 */
public interface IOProtocolWriter extends IOProtocol {

    /**
     * The only method required by the {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}.
     * Specifies how to write the processed {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} to the output.
     *
     * @param bundle the proccesed {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} to be written to the output.
     * */
    public void write( Bundle bundle );
}
