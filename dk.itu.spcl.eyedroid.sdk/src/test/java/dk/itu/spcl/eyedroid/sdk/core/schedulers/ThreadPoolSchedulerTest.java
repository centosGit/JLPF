package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.impl.ComputableImplementation;
import dk.itu.spcl.eyedroid.sdk.impl.FilterImplementation;
import dk.itu.spcl.eyedroid.sdk.impl.ThreadPerFilterSchedulerTestImpl;
import junit.framework.TestCase;

/**
 * Created by centos on 10/14/14.
 */
public class ThreadPoolSchedulerTest extends TestCase {


    ThreadPerFilterSchedulerTestImpl scheduler;
    FilterImplementation filter1;
    FilterImplementation filter2;
    FilterImplementation filter3;
    FilterImplementation filter4;
    FilterImplementation filter5;

    ComputableImplementation computable;

    final int THREADS = 5;

    public ThreadPoolSchedulerTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        filter1 = new FilterImplementation();
        filter1.setFilterName("1");
        filter2 = new FilterImplementation();
        filter2.setFilterName("2");
        filter3 = new FilterImplementation();
        filter3.setFilterName("3");
        filter4 = new FilterImplementation();
        filter4.setFilterName("4");
        filter5 = new FilterImplementation();
        filter5.setFilterName("5");

        computable = new ComputableImplementation();

        computable.addFilter(filter1);
        computable.addFilter(filter2);
        computable.addFilter(filter3);
        computable.addFilter(filter4);
        computable.addFilter(filter5);

        computable.setupFilterPipes(1);

        scheduler = new ThreadPerFilterSchedulerTestImpl();


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private void insertBundle(String message){
        Bundle bundle = new Bundle();
        bundle.put(FilterImplementation.MESSAGE , message);
        computable.pushToSource(bundle);

    }

    public void testStartThreadPool(){
        scheduler.startScheduler(computable);
        insertBundle("0");
        assertNotNull("Output bundle is null", computable.popFromSink());
    }

    public void testNumberOfThreadsRunning(){

        int beforeNumThreads = Thread.activeCount();
        scheduler.startScheduler(computable);
        insertBundle("0");
        assertEquals("Number of threads started is not " + String.valueOf(THREADS) , beforeNumThreads + THREADS , Thread.activeCount());
        scheduler.stop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("Scheduler thread is still running" , beforeNumThreads , Thread.activeCount());
    }

    public void testOrderOfBundles(){
        scheduler.startScheduler(computable);
        insertBundle("0");
        Bundle bundle = computable.popFromSink();
        assertNotNull("Bundle is null" , bundle);
        assertEquals("Execution order is wrong" , "012345" , (String)bundle.get(FilterImplementation.MESSAGE));
    }


}
