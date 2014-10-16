package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.Filter;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Parallel scheduler implementation. Executes filters in a customizable size thread pool.
 */

public class ThreadPerFilterScheduler extends Scheduler {

    protected int mSize;                 //Thread pool size
    protected ExecutorService mExecutor; //Main executor

    /**
     * Execute {@link dk.itu.spcl.eyedroid.sdk.core.Computable} instance.
     *
     * @param computable Computable object containing the filter structure.
     */
    @Override
    protected void start(Computable computable) {

        List<Filter> mFilterList = computable.getFilterList();
        mExecutor = Executors.newFixedThreadPool(mFilterList.size());

        for (final Filter filter : mFilterList) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    while (!mExecutor.isShutdown())
                        filter.run();
                }
            };
            mExecutor.execute(runnable);
        }
    }

    /**
     * Stop current scheduler
     */
    @Override
    protected void stop() {
        mExecutor.shutdownNow();

    }
}
