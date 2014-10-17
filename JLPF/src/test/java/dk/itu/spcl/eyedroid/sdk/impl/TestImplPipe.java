package dk.itu.spcl.eyedroid.sdk.impl;

import dk.itu.spcl.eyedroid.sdk.core.pipes.BlockingPipe;

public class TestImplPipe extends BlockingPipe {

    public int getPipeSize(){
        return mInternalQueue.size();
    }
}