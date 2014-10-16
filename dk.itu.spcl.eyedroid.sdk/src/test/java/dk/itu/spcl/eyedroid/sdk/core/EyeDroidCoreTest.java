package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.impl.FilterImplementation;
import junit.framework.TestCase;

/**
 * Created by centos on 10/16/14.
 */
public class EyeDroidCoreTest extends TestCase {

    FilterImplementation filter1;
    FilterImplementation filter2;
    FilterImplementation filter3;
    FilterImplementation filter4;
    FilterImplementation filter5;

    EyeDroidCore core;

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

        core = new EyeDroidCore();

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

    public EyeDroidCoreTest(String name) {
        super(name);
    }

    private void insertBundle(String message){
        Bundle bundle = new Bundle();
        bundle.put(FilterImplementation.MESSAGE , message);
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

        assertEquals("Output is in wrong order" , "012345" , bundle.get(FilterImplementation.MESSAGE));
    }


}
