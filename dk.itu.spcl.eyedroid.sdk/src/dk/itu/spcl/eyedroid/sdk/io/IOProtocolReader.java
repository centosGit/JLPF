package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Created by centos on 10/10/14.
 */
public interface IOProtocolReader extends IOProtocol {

    public Bundle read();
}
