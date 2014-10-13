package dk.itu.spcl.eyedroid.sdk.test;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.*;

public class Main {

    public static final String COUNTER = "counter";

    public static void main(String[] args) {

        Filter filter1 = new TestFilter();
        filter1.setFilterName("filter 1");
        Filter filter2 = new TestFilter();
        filter2.setFilterName("filter 2");
        Filter filter3 = new TestFilter();
        filter3.setFilterName("filter 3");
        Filter filter4 = new TestFilter();
        filter4.setFilterName("filter 4");
        Filter filter5 = new TestFilter();
        filter5.setFilterName("filter 5");
        Filter filter6 = new TestFilter();
        filter6.setFilterName("filter 6");
        Filter filter7 = new TestFilter();
        filter7.setFilterName("filter 7");

        FilterComposite filterCompo = new FilterComposite();
        filterCompo.addFilter(filter3);
        filterCompo.addFilter(filter4);
        filterCompo.addFilter(filter5);

        FilterComposite filterCompo2 = new FilterComposite();
        filterCompo2.addFilter(filter6);
        filterCompo2.addFilter(filter7);

        filterCompo.addFilter(filterCompo2);

        Computable computable = new Computable();
        computable.addFilter(filter1);
        computable.addFilter(filter2);
        computable.addFilter(filterCompo);

        Scheduler scheduler = new TestScheduler(computable);
        final EyeDroidCore core = new EyeDroidCore(scheduler, computable);
        core.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Bundle bundle = core.popBundle();
                    System.out.println("from sink : " + bundle.get(COUNTER));
                }
            }
        }).start();

        int counter = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bundle bundle = new Bundle();
            bundle.put(COUNTER, counter++);
            core.pushBundle(bundle);
        }
    }
}