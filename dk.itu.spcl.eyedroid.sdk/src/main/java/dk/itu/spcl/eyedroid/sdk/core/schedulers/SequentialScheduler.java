package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.Filter;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;

import java.util.List;
import java.util.concurrent.*;

/**
 * Sequential scheduler implementation. Executes sequentially (in the same thread) every filter given by the computable
 * object.
 */

public class SequentialScheduler extends Scheduler {

    private ExecutorService mExecutor;  //Main executor

    /**
     * Execute {@link dk.itu.spcl.eyedroid.sdk.core.Computable} instance.
     * @param computable Computable object containing the filter structure.
     */
    @Override
    protected void start(Computable computable) {

        mExecutor = Executors.newSingleThreadExecutor();
        final List<Filter> mFilterList = computable.getFilterList();

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

    /**
     * Stop current scheduler
     */
    @Override
    protected void stop() {
        mExecutor.shutdownNow();
    }
}
