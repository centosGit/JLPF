package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import junit.framework.TestCase;

public class FilterTest extends TestCase{

    private Filter filter;
    private Pipe inputPipe;
    private Pipe outputPipe;

    public FilterTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        filter = new Filter() {
            @Override
            protected Bundle execute(Bundle bundle) {
                return bundle;
            }
        };

        inputPipe = new Pipe();
        outputPipe = new Pipe();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRun(){
        filter.setInput(inputPipe);
        filter.setOutput(outputPipe);
        assertFalse("Filter is in started mode before starting" , filter.hasStarted());

        inputPipe.push(new Bundle());
        filter.run();

        assertTrue("Filter is not in started mode after running" , filter.hasStarted());

        assertNotNull("Bundle from output filter in null" , outputPipe.pop());
    }

}
