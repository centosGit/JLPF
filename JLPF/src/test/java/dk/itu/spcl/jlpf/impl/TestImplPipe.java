package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.pipes.BlockingPipe;

public class TestImplPipe extends BlockingPipe {

    public int getPipeSize(){
        return mInternalQueue.size();
    }
}