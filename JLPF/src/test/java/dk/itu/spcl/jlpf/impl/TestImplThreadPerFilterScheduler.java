package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.Computable;
import dk.itu.spcl.jlpf.core.schedulers.ThreadPerFilterScheduler;

public class TestImplThreadPerFilterScheduler extends ThreadPerFilterScheduler {

    public void startScheduler(Computable computable){
        super.start(computable);
    }
}
