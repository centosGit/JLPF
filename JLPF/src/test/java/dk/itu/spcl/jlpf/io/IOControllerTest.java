package dk.itu.spcl.jlpf.io;

import dk.itu.spcl.jlpf.core.ProcessingCore;
import dk.itu.spcl.jlpf.impl.TestImplFilter;
import dk.itu.spcl.jlpf.impl.TestImplIOController;
import dk.itu.spcl.jlpf.impl.TestImplRWProtocol;
import junit.framework.TestCase;

public class IOControllerTest extends TestCase {

    TestImplRWProtocol protocolRW;
    TestImplIOController ioController;
    IORWDefaultImpl ioRW;
    ProcessingCore core;

    TestImplFilter filter1;

    public IOControllerTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //Create a test filter
        filter1 =  new TestImplFilter();
        filter1.setFilterName("1");

        //Start core
        core = new ProcessingCore(10);
        core.addFilter(filter1);
        core.start(1);

        //Setup IO
        protocolRW = new TestImplRWProtocol();
        ioRW = new IORWDefaultImpl(protocolRW,protocolRW);
        ioController = new TestImplIOController(core,ioRW,ioRW);
        ioController.setupController();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        core.stop();
        ioController.stop();
    }

    //Test read and write operations when starting, stopping and restarting IO controller.
    public void testReadWrite() {
        ioController.start();
        assertEquals("Read and write operation called unequal on protocols",
                protocolRW.countRead, protocolRW.countWrite);
        ioController.stop();
        assertEquals("Protocols cleaned",0 ,protocolRW.countRead);
        ioController.start();
        assertEquals("Read and write operation called unequal on protocols after stop",
                protocolRW.countRead,protocolRW.countWrite);
        ioController.restart();
        assertEquals("Read and write operation called unequal on protocols after restart",
                protocolRW.countRead,protocolRW.countWrite);
    }
}
