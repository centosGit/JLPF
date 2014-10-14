package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.Filter;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by centos on 10/13/14.
 */

public class ThreadPoolScheduler extends Scheduler {

    protected int mSize;
    protected ExecutorService mExecutor;

    public ThreadPoolScheduler( int size ) {
        mSize = size;
    }

    @Override
    protected void start(Computable computable) {

        List<Filter> mFilterList = computable.getFilterList();
        mExecutor = Executors.newFixedThreadPool(mFilterList.size());

        for( final Filter filter : mFilterList ){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    while(!mExecutor.isShutdown())
                        filter.run();
                }
            };

            mExecutor.execute(runnable);
        }

    }

    @Override
    protected void stop() {
        mExecutor.shutdownNow();

    }
}
