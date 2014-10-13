package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;

/**
 * Created by centos on 10/13/14.
 */
public class ThreadPerFilterScheduler extends Scheduler {
    public ThreadPerFilterScheduler(Computable computable) {
        super(computable);
    }

    @Override
    protected void start() {

    }

    @Override
    protected void stop() {

    }
}
