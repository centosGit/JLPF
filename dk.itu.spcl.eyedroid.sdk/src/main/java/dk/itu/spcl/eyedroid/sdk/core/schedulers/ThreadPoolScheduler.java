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
public class ThreadPoolScheduler extends Scheduler {

    protected ExecutorService mExecutor; //Main executor
    private int mSize;
    List<Filter> mFilterList;

    public ThreadPoolScheduler( int threads ){
        mSize = threads;
    }
    /**
     * Execute {@link dk.itu.spcl.eyedroid.sdk.core.Computable} instance.
     *
     * @param computable Computable object containing the filter structure.
     */
    @Override
    protected void start(Computable computable) {
        mFilterList = computable.getFilterList();
        mExecutor = Executors.newFixedThreadPool(mSize);

        CustomRunnable r = new CustomRunnable(0);

        mExecutor.execute(r);
    }
    /**
     * Stop current scheduler
     */
    @Override
    protected void stop() {
        mExecutor.shutdownNow();
    }

    public class CustomRunnable implements Runnable {

        private int mCurrentPosition;

        CustomRunnable(int position){
            mCurrentPosition = position;
        }

        @Override
        public void run() {
            Filter filter = mFilterList.get(mCurrentPosition);
            filter.run();

            if( mCurrentPosition == 0){
                CustomRunnable r1 = new CustomRunnable(mCurrentPosition);
                CustomRunnable r2 = new CustomRunnable(mCurrentPosition + 1);

                mExecutor.execute(r1);
                mExecutor.execute(r2);
            }else if( mCurrentPosition < mFilterList.size() - 1 ){
                CustomRunnable r = new CustomRunnable(mCurrentPosition + 1);
                mExecutor.execute(r);
            }

        }
    }
}
