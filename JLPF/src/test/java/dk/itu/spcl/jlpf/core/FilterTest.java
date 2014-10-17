package dk.itu.spcl.jlpf.core;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.core.pipes.BlockingPipe;
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

        inputPipe = new BlockingPipe();
        outputPipe = new BlockingPipe();
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
