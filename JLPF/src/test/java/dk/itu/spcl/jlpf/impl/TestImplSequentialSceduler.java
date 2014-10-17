package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.Computable;
import dk.itu.spcl.jlpf.core.schedulers.SequentialScheduler;

/**
 * Created by centos on 10/16/14.
 */
public class TestImplSequentialSceduler extends SequentialScheduler{


    public void startScheduler( Computable computable){
        super.start(computable);
    }
}
