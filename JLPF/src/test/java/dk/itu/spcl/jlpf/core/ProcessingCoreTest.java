package dk.itu.spcl.jlpf.core;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.impl.TestImplFilter;
import junit.framework.TestCase;

/**
 * Created by centos on 10/16/14.
 */
public class ProcessingCoreTest extends TestCase {

    TestImplFilter filter1;
    TestImplFilter filter2;
    TestImplFilter filter3;
    TestImplFilter filter4;
    TestImplFilter filter5;

    ProcessingCore core;

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

        core = new ProcessingCore();

        core.addFilter(filter1);
        core.addFilter(filter2);
        core.addFilter(filter3);
        core.addFilter(filter4);
        core.addFilter(filter5);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public ProcessingCoreTest(String name) {
        super(name);
    }

    private void insertBundle(String message){
        Bundle bundle = new Bundle();
        bundle.put(TestImplFilter.MESSAGE , message);
        core.pushBundle(bundle);

    }

    public void testIsRunningSequential(){
        int beforeThreads = Thread.activeCount();

        insertBundle("0");
        core.start(1);

        assertEquals("Core is running extra threads" , beforeThreads + 1 , Thread.activeCount());

        core.stop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Core is running extra threads" , beforeThreads , Thread.activeCount());

    }

    public void testIsRunningThreadPerFilter(){
        int beforeThreads = Thread.activeCount();

        insertBundle("0");
        core.start(5);

        assertEquals("Core is running extra threads" , beforeThreads + 5 , Thread.activeCount());

        core.stop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Core is running extra threads" , beforeThreads , Thread.activeCount());
    }

    public void testCoreOutputOrder(){
        insertBundle("0");
        core.start(5);

        Bundle bundle = core.popBundle();

        assertEquals("Output is in wrong order" , "012345" , bundle.get(TestImplFilter.MESSAGE));
    }


}
