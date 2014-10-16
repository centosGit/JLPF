package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.schedulers.SequentialScheduler;
import dk.itu.spcl.eyedroid.sdk.core.schedulers.ThreadPerFilterScheduler;

import java.util.List;

/**
 * EyeDroidCore class is the main implementation of the sdk.
 * The class provides a facade to control the execution of an {@link dk.itu.spcl.eyedroid.sdk.core.Computable} object
 * by an {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler}.
 */

public class EyeDroidCore {

    private Computable mComputable;     //Computable object used by the scheduler
    private Scheduler mScheduler;       //Scheduler executor

    /**
     * Set the {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler}-{@link dk.itu.spcl.eyedroid.sdk.core.Computable} pair to
     * be executed by the core.
     */
    public EyeDroidCore() {
        mComputable = new Computable();
    }

    /**
     * Add new filter to be executed by the core.
     *
     * @param filter New filter
     * @return Return true if filter added successfully.
     */
    public boolean addFilter(Filter filter) {
        return mComputable.addFilter(filter);
    }

    /**
     * Remove filter from core.
     *
     * @param id Filter id
     * @return Filter removed. Null if filter is not found.
     */
    public Filter removeFilter(int id) {
        return mComputable.removeFilter(id);
    }

    /**
     * Start {@link dk.itu.spcl.eyedroid.sdk.core.Scheduler} object.
     *
     * @param numberOfThreads Number of threads used by the core to execute the filters. The number of threads must not
     *                        exceed the number of filters.
     * @throws RuntimeException
     */
    public void start(int numberOfThreads) throws RuntimeException {

        List<Filter> list = mComputable.getFilterList();

        if (numberOfThreads == 1)
            mScheduler = new SequentialScheduler();
        else if (numberOfThreads <= list.size())
            mScheduler = new ThreadPoolScheduler(numberOfThreads);
        else
            throw new RuntimeException("Number of threads is wrong");

        mComputable.setupFilterPipes(numberOfThreads);

        mScheduler.innerStart(mComputable);
    }

    /**
     * Stop the scheduler and cleanup the computable instance.
     */
    public void stop() {
        mScheduler.innerStop();
        mComputable.cleanup();
    }

    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the core to be processed.
     *
     * @param bundle Wrapped object to be processed.
     */
    public void pushBundle(Bundle bundle) {
        mComputable.pushToSource(bundle);
    }

    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object processed by the core.
     *
     * @return Return wrapped processing result.
     */
    public Bundle popBundle() {
        return mComputable.popFromSink();
    }
}
