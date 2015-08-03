package dk.itu.spcl.jlpf.io;

import dk.itu.spcl.jlpf.common.Bundle;

import java.io.IOException;

/**
 * Base interface for every object that wants to act as a {@link dk.itu.spcl.jlpf.io.IOProtocolReader}.
 * This class should be passed to a {@link dk.itu.spcl.jlpf.io.InputReader} instance and used by the
 * {@link dk.itu.spcl.jlpf.io.InputReader}.
 */
public interface IOProtocolReader extends IOProtocol {

    /**
     * The only method required by the {@link dk.itu.spcl.jlpf.io.InputReader}.
     * Specified how to read the {@link dk.itu.spcl.jlpf.common.Bundle} from the source.
     *
     * @return {@link dk.itu.spcl.jlpf.common.Bundle} next Bundle read from the source.
     * */
    public Bundle read() throws IOException;
}
