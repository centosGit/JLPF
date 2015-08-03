package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.Computable;
import dk.itu.spcl.jlpf.core.schedulers.ThreadPoolScheduler;

public class TestImplThreadPoolScheduler extends ThreadPoolScheduler {

    public TestImplThreadPoolScheduler(int threads) {
        super(threads);
    }

    public void startScheduler(Computable computable){
        super.start(computable);
    }
}
