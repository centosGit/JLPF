package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 *
 *
 */
public interface InputReader {

    public void setProtocolReader( IOProtocolReader protocolReader );

    public Bundle readInput();
}
