package dk.itu.spcl.eyedroid.sdk.impl;

import dk.itu.spcl.eyedroid.sdk.core.Pipe;

/**
 * Created by centos on 10/13/14.
 */
public class PipeImplementation extends Pipe {

    public int getPipeSize(){
        return mInternalQueue.size();
    }
}
