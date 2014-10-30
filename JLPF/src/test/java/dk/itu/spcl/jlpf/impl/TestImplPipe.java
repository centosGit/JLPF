package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.pipes.BlockingPipe;

public class TestImplPipe extends BlockingPipe {

    public TestImplPipe(int capacity) {
        super(capacity);
    }

    public int getPipeSize(){
        return mInternalQueue.size();
    }
}