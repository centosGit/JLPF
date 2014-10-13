package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.impl.CompositeImplementation;
import dk.itu.spcl.eyedroid.sdk.impl.FilterImplementation;
import junit.framework.TestCase;

public class FilterCompositeTest extends TestCase{






    Filter filter1;
    Filter filter2;
    Filter filter3;
    Filter filter4;
    Filter filter5;

    Pipe inputPipe;
    Pipe outputPipe;

    CompositeImplementation composite;
    FilterComposite innerComposite;


    public FilterCompositeTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        composite = new CompositeImplementation();
        innerComposite = new FilterComposite();

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

        inputPipe = new Pipe();
        outputPipe = new Pipe();

        composite.setInput(inputPipe);
        composite.setOutput(outputPipe);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testAddFilter(){
        assertTrue("Could not add initial filter" , composite.addFilter(filter1));
        assertFalse("Same filter added in the composite", composite.addFilter(filter1));

        assertEquals("Size of composite is not one" , 1 , composite.compositeSize());

        assertTrue("Could not add initial filter" , composite.addFilter(filter2));

        assertEquals("Size of composite is not one" , 2 , composite.compositeSize());

        composite.setStarted();

        assertFalse("Added filter after the composite started", composite.addFilter(filter2));
    }

    public void testRemoveFilter(){

        assertTrue("Could not add initial filter" , composite.addFilter(filter1));
        assertTrue("Could not add initial filter" , composite.addFilter(filter2));

        assertEquals("Size of composite is not one" , 2 , composite.compositeSize());

        assertNotNull("Removed object is null" , composite.removeFilter(filter1.getFilterId()));

        assertEquals("Size of composite is not one" , 1 , composite.compositeSize());

        assertNotNull("Removed object is null" , composite.removeFilter(filter2.getFilterId()));

        assertEquals("Size of composite is not one" , 0 , composite.compositeSize());


        assertTrue("Could not add initial filter" , composite.addFilter(filter1));
        assertTrue("Could not add initial filter" , composite.addFilter(filter2));

        composite.setStarted();

        assertNull("Removed object is not null", composite.removeFilter(filter2.getFilterId()));

        assertEquals("Size of composite is not one" , 2 , composite.compositeSize());

    }

    public void testFilterOrder(){
        assertTrue("Could not add initial filter" , composite.addFilter(filter1));
        assertTrue("Could not add initial filter" , composite.addFilter(filter2));

        assertEquals("Map and list don't have the same size" ,
                composite.getList().size() , composite.getMap().size());

        assertEquals("Filter1 is not in the first position" ,
                filter1.getFilterId() , composite.getList().get(0).getFilterId());
        assertEquals("Filter2 is not in the second position" ,
                filter2.getFilterId() , composite.getList().get(1).getFilterId());


       composite.removeFilter(filter1.getFilterId());

        assertEquals("Filter2 is not in the first position" ,
                filter2.getFilterId() , composite.getList().get(0).getFilterId());

        assertEquals("Map and list don't have the same size" ,
                composite.getList().size() , composite.getMap().size());

        composite.addFilter(filter1);
        composite.addFilter(filter3);

        //order 2-1-3
        composite.removeFilter(filter1.getFilterId());

        assertEquals("Map and list don't have the same size" ,
                composite.getList().size() , composite.getMap().size());

        assertEquals("Filter2 is not in the first position" ,
                filter2.getFilterId() , composite.getList().get(0).getFilterId());

        assertEquals("Filter3 is not in the first position" ,
                filter3.getFilterId() , composite.getList().get(1).getFilterId());
    }

    public void testRun(){

        inputPipe.push(new Bundle());
        composite.addFilter(filter1);
        composite.addFilter(filter2);
        composite.addFilter(filter3);

        assertFalse("Composite is in started mode before execution" , composite.hasStarted());
        composite.run();
        assertTrue("Composite is not started mode after execution", composite.hasStarted());

        assertNotNull("Bundle in output is null" , outputPipe.pop());

    }

    public void testRunOrder(){
        composite.addFilter(filter1);
        composite.addFilter(filter2);
        innerComposite.addFilter(filter3);
        innerComposite.addFilter(filter4);
        composite.addFilter(innerComposite);
        composite.addFilter(filter5);

        Bundle bundle = new Bundle();
        bundle.put(FilterImplementation.MESSAGE , "0");

        inputPipe.push(bundle);

        composite.run();

        bundle = outputPipe.pop();

        assertNotNull("Bundle in output is null" , bundle );

        assertEquals("Execution order in composite is not correct" , "012345" ,
                ((String)bundle.get(FilterImplementation.MESSAGE)));

    }

    public void testStartMode(){
        composite.addFilter(filter1);
        composite.addFilter(filter2);
        innerComposite.addFilter(filter3);
        innerComposite.addFilter(filter4);
        composite.addFilter(innerComposite);
        composite.addFilter(filter5);

        Bundle bundle = new Bundle();
        bundle.put(FilterImplementation.MESSAGE , "0");

        inputPipe.push(bundle);
        composite.run();

        assertTrue("Filter is not in start mode after executing" , composite.hasStarted());
        assertTrue("Filter is not in start mode after executing", innerComposite.hasStarted());
        assertTrue("Filter is not in start mode after executing", filter1.hasStarted());
        assertTrue("Filter is not in start mode after executing", filter2.hasStarted());
        assertTrue("Filter is not in start mode after executing", filter3.hasStarted());
        assertTrue("Filter is not in start mode after executing", filter4.hasStarted());
        assertTrue("Filter is not in start mode after executing", filter5.hasStarted());
    }




}