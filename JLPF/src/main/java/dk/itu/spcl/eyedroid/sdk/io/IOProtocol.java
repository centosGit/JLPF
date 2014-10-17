package dk.itu.spcl.eyedroid.sdk.io;

/**
 * Base class for every kind of protocol. Protocol implementations should
 * specify how a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} is read and written.
 */
public interface IOProtocol {

    /**
     * Method used to bring the protocol implementation in a state ready to be used.
     * */
    public void init();

    /**
     * Method used to close any open resources. For example sockets if the protocol is
     * a network implementation or files if it is a local implementation.
     * */
    public void cleanup();
}
