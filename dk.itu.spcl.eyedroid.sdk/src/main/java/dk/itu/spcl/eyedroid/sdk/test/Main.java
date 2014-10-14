package dk.itu.spcl.eyedroid.sdk.test;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore;
import dk.itu.spcl.eyedroid.sdk.core.Scheduler;
import dk.itu.spcl.eyedroid.sdk.test.impl.ComputableImplementation;
import dk.itu.spcl.eyedroid.sdk.test.impl.FilterImplementation;

/**
 * Created by centos on 10/14/14.
 */
public class Main {

    static Scheduler scheduler;
    static FilterImplementation filter1;
    static FilterImplementation filter2;
    static FilterImplementation filter3;
    static FilterImplementation filter4;
    static FilterImplementation filter5;
    static ComputableImplementation computable;
    static EyeDroidCore core;

    public static void main(String[] args) {


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


        core.start(4);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    core.pushBundle(new Bundle());
                }
            }
        });
        thread.start();

        int counter = 0;

        while( counter++ < 5 ){
            Bundle bundle = core.popBundle();
            System.out.println((String)bundle.get(FilterImplementation.MESSAGE));
        }

        core.stop();
        core.start(1);
        System.out.println("restarting");


        while( counter++ < 10 ){
            Bundle bundle = core.popBundle();
            System.out.println((String)bundle.get(FilterImplementation.MESSAGE));
        }

    }


}
