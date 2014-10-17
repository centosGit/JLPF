package dk.itu.spcl.eyedroid.sdk.impl;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.schedulers.SequentialScheduler;

/**
 * Created by centos on 10/16/14.
 */
public class TestImplSequentialSceduler extends SequentialScheduler{


    public void startScheduler( Computable computable){
        super.start(computable);
    }
}
