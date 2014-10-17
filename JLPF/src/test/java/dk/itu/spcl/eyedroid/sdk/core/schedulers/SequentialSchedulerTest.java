package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.impl.TestImplComputable;
import dk.itu.spcl.eyedroid.sdk.impl.TestImplFilter;
import dk.itu.spcl.eyedroid.sdk.impl.TestImplSequentialSceduler;
import junit.framework.TestCase;

/**
 * Created by centos on 10/14/14.
 */
public class SequentialSchedulerTest extends TestCase {


    TestImplSequentialSceduler scheduler;
    TestImplFilter filter1;
    TestImplFilter filter2;
    TestImplFilter filter3;
    TestImplFilter filter4;
    TestImplFilter filter5;

    TestImplComputable computable;


    public SequentialSchedulerTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        filter1 = new TestImplFilter();
        filter1.setFilterName("1");
        filter2 = new TestImplFilter();
        filter2.setFilterName("2");
        filter3 = new TestImplFilter();
        filter3.setFilterName("3");
        filter4 = new TestImplFilter();
        filter4.setFilterName("4");
        filter5 = new TestImplFilter();
        filter5.setFilterName("5");

        computable = new TestImplComputable();

        computable.addFilter(filter1);
        computable.addFilter(filter2);
        computable.addFilter(filter3);
        computable.addFilter(filter4);
        computable.addFilter(filter5);

        computable.setupFilterPipes(1);

        scheduler = new TestImplSequentialSceduler();


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private void insertBundle(String message){
        Bundle bundle = new Bundle();
        bundle.put(TestImplFilter.MESSAGE , message);
        computable.pushToSource(bundle);

    }

    public void testStartSequential(){
        scheduler.startScheduler(computable);
        insertBundle("0");
        assertNotNull("Output bundle is null", computable.popFromSink());
    }

    public void testOnlyOneThreadRuns(){
        int beforeNumThreads = Thread.activeCount();
        scheduler.startScheduler(computable);
        insertBundle("0");
        assertEquals("Number of threads started is not 1" , beforeNumThreads + 1 , Thread.activeCount());
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
        assertEquals("Execution order is wrong" , "012345" , (String)bundle.get(TestImplFilter.MESSAGE));
    }


}
