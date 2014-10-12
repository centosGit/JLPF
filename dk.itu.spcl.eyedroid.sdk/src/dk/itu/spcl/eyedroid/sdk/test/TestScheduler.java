package dk.itu.spcl.eyedroid.sdk.test;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.Filter;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by centos on 10/12/14.
 */
public class TestScheduler extends Scheduler {

    private Executor mExecutor;
    private List<Filter> mFilterList;


    public TestScheduler(Computable computable) {
        super(computable);
//        mExecutor = new Executor() {
//            private Thread mThread;
//            @Override
//            public void execute(Runnable runnable) {
//                new Thread(runnable).start();
//            }
//        };

        mExecutor  = Executors.newSingleThreadExecutor();


        mFilterList = computable.getFilterList();
    }

    @Override
    protected void start() {

//        for( final Filter filter : mFilterList ){
//            Runnable r = new Runnable() {
//                @Override
//                public void run() {
//                    while(true)
//                        filter.run();
//                }
//            };
//
//            mExecutor.execute(r);
//        }
//

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(true) {
                    for (final Filter filter : mFilterList) {
                        filter.run();
                    }
                }
            }
        };

        mExecutor.execute(r);


    }

    @Override
    public void stop() {

    }
}
