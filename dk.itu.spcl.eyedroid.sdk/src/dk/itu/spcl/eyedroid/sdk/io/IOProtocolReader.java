package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Base interface for every object that wants to act as a {@link dk.itu.spcl.eyedroid.sdk.io.IOProtocolReader}.
 * This class should be passed to a {@link dk.itu.spcl.eyedroid.sdk.io.InputReader} instance and used by the
 * {@link dk.itu.spcl.eyedroid.sdk.io.InputReader}.
 */
public interface IOProtocolReader extends IOProtocol {

    /**
     * The only method required by the {@link dk.itu.spcl.eyedroid.sdk.io.InputReader}.
     * Specified how to read the {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} from the source.
     *
     * @return {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} next Bundle read from the source.
     * */
    public Bundle read();
}
