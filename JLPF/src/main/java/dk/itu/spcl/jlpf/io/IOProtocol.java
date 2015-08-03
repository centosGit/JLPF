package dk.itu.spcl.jlpf.io;

import java.io.IOException;

/**
 * Base class for every kind of protocol. Protocol implementations should
 * specify how a {@link dk.itu.spcl.jlpf.common.Bundle} is read and written.
 */
public interface IOProtocol {

    /**
     * Method used to bring the protocol implementation in a state ready to be used.
     * */
    public void init() throws IOException;

    /**
     * Method used to close any open resources. For example sockets if the protocol is
     * a network implementation or files if it is a local implementation.
     * */
    public void cleanup();
}
