package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.impl.TestImplComposite;
import dk.itu.spcl.eyedroid.sdk.impl.TestImplComputable;
import dk.itu.spcl.eyedroid.sdk.impl.TestImplFilter;
import dk.itu.spcl.eyedroid.sdk.impl.TestImplPipe;
import junit.framework.TestCase;

public class ComputableTest extends TestCase {

    Filter filter1;
    Filter filter2;
    Filter filter3;
    Filter filter4;
    Filter filter5;

    TestImplComposite composite1;
    TestImplComposite composite2;
    TestImplComputable computable;

    public ComputableTest(String name) {
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

        composite1 = new TestImplComposite();
        composite2 = new TestImplComposite();
        computable = new TestImplComputable();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddFilter() {
        assertTrue("Filter was not added", computable.addFilter(filter1));
        assertTrue("Computable is not consistent", computable.isConsistent());
        assertFalse("Filter was added twice", computable.addFilter(filter1));
        assertTrue("Filter was not added", computable.addFilter(filter2));
        assertTrue("Computable is not consistent", computable.isConsistent());
        assertEquals("Size of computable is not right", 2, computable.getComputableSize());
    }

    public void testRemoveFilter() {
        computable.addFilter(filter1);
        computable.addFilter(filter2);
        computable.addFilter(filter3);

        assertNotNull("Filter was not removed", computable.removeFilter(filter1.getFilterId()));
        assertTrue("Computable is not consistent", computable.isConsistent());
        assertEquals("Size of computable is not right", 2, computable.getComputableSize());

        assertNull("Filter was not removed", computable.removeFilter(filter1.getFilterId()));
        assertTrue("Computable is not consistent", computable.isConsistent());
        assertEquals("Size of computable is not right", 2, computable.getComputableSize());

        assertNotNull("Filter was not removed", computable.removeFilter(filter2.getFilterId()));
        assertTrue("Computable is not consistent", computable.isConsistent());
        assertEquals("Size of computable is not right", 1, computable.getComputableSize());
    }

    public void testFilterOrder() {
        setUpComputable1();
        insertFirstBundle("0");

        computable.setupFilterPipes(1);
        computable.getFilterList();
        composite1.run();

        Bundle bundle = computable.popFromSink();
        assertEquals("Computable execution order is not correct","012345",
                ((String) bundle.get(TestImplFilter.MESSAGE)));
    }

    public void testCoreExecutionOutput() {
        setUpComputable1();
        insertFirstBundle("0");

        computable.setupFilterPipes(1);
        computable.getFilterList();
        composite1.run();

        assertNotNull("Output of core is null", computable.popFromSink());
    }

    public void testCleanUp() {
        setUpComputable2();
        insertFirstBundle("");
        insertFirstBundle("");

        TestImplPipe pipe1 = new TestImplPipe();
        TestImplPipe pipe2 = new TestImplPipe();
        TestImplPipe pipe3 = new TestImplPipe();

        filter1.setInput(computable.getSource());
        filter1.setOutput(pipe1);

        filter2.setInput(pipe1);
        filter2.setOutput(pipe2);

        filter3.setInput(pipe2);
        filter3.setOutput(pipe3);

        filter4.setInput(pipe3);
        filter4.setOutput(computable.getSink());

        pipe1.push(new Bundle());
        pipe2.push(new Bundle());

        pipe1.push(new Bundle());
        pipe2.push(new Bundle());

        pipe3.push(new Bundle());

        computable.getSink().push(new Bundle());

        computable.cleanup();

        assertEquals("Pipe is not cleaned up", 0, pipe1.getPipeSize());
        assertEquals("Pipe is not cleaned up", 0, pipe2.getPipeSize());
        assertEquals("Pipe is not cleaned up", 0, pipe3.getPipeSize());
    }

    private void insertFirstBundle(String message) {
        Bundle bundle = new Bundle();
        bundle.put(TestImplFilter.MESSAGE, message);
        computable.pushToSource(bundle);
    }

    private void setUpComputable1() {
        composite1.addFilter(filter1);
        composite1.addFilter(filter2);
        composite1.addFilter(filter3);
        computable.addFilter(composite1);
        composite2.addFilter(filter4);
        composite2.addFilter(filter5);
        composite1.addFilter(composite2);
    }

    private void setUpComputable2() {
        computable.addFilter(filter1);
        computable.addFilter(filter2);
        computable.addFilter(filter3);
        computable.addFilter(filter4);
    }
}
