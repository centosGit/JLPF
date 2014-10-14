package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.Filter;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by centos on 10/13/14.
 */
public class SequentialScheduler extends Scheduler {

    private ExecutorService mExecutor;
    private List<Filter> mFilterList;

    @Override
    protected void start(Computable computable) {

        mExecutor = Executors.newSingleThreadExecutor();
        mFilterList = computable.getFilterList();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!mExecutor.isShutdown()) {
                    for (final Filter filter : mFilterList) {
                        filter.run();
                    }

                }
            }
        };

        mExecutor.execute(runnable);
    }

    @Override
    protected void stop() {
        mExecutor.shutdownNow();
    }
}
