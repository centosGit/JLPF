package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Created by Daniel on 10/10/2014.
 */
public interface Pipe {

    public void push (Bundle bundle);
    public Bundle pop ();
    public void cleanup ();
}