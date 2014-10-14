package dk.itu.spcl.eyedroid.sdk.core.schedulers;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;
import dk.itu.spcl.eyedroid.sdk.impl.ComputableImplementation;
import dk.itu.spcl.eyedroid.sdk.impl.FilterImplementation;
import junit.framework.TestCase;

/**
 * Created by centos on 10/14/14.
 */
public class SequentialSchedulerTest extends TestCase {


    Scheduler scheduler;
    FilterImplementation filter1;
    FilterImplementation filter2;
    FilterImplementation filter3;
    FilterImplementation filter4;
    FilterImplementation filter5;

    ComputableImplementation computable;

    EyeDroidCore core;


    public SequentialSchedulerTest(String name) {
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

        scheduler = new SequentialScheduler();

        core = new EyeDroidCore();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private void insertBundle(String message){
        Bundle bundle = new Bundle();
        bundle.put(FilterImplementation.MESSAGE , message);
        core.pushBundle(bundle);

    }

    public void testStartSequential(){

        core.start(1);
        insertBundle("0");
        assertNotNull("Output bundle is null" , core.popBundle());

    }

    public void testStopSequential(){

        core.start(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                insertBundle("0");
            }
        });
        thread.start();
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        core.stop();
        while(true);

//        assertFalse("Scheduler did not stop" , scheduler.isRunning());

    }
}
