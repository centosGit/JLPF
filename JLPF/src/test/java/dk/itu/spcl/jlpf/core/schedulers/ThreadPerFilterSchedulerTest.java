package dk.itu.spcl.jlpf.core.schedulers;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.impl.TestImplComputable;
import dk.itu.spcl.jlpf.impl.TestImplFilter;
import dk.itu.spcl.jlpf.impl.TestImplThreadPerFilterScheduler;
import junit.framework.TestCase;

public class ThreadPerFilterSchedulerTest extends TestCase {

    TestImplThreadPerFilterScheduler scheduler;
    TestImplFilter filter1;
    TestImplFilter filter2;
    TestImplFilter filter3;
    TestImplFilter filter4;
    TestImplFilter filter5;

    TestImplComputable computable;

    final int THREADS = 5;

    public ThreadPerFilterSchedulerTest(String name) {
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

        scheduler = new TestImplThreadPerFilterScheduler();
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
        assertEquals("Execution order is wrong" , "012345" , (String)bundle.get(TestImplFilter.MESSAGE));
    }
}
