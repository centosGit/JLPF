package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.ProcessingCore;
import dk.itu.spcl.jlpf.io.InputReader;
import dk.itu.spcl.jlpf.io.OutputWriter;
import dk.itu.spcl.jlpf.io.IOController;

/**
 * Created by Daniel on 10/16/2014.
 */
public class TestImplIOController extends IOController{

    public TestImplIOController(ProcessingCore core, InputReader reader, OutputWriter writer){
        super(core,reader,writer);
    }

    @Override
    public void init(){

    }

    @Override
    public void onExecute(){
        super.read();
        super.read();
        super.read();
        super.read();
        super.write();
        super.write();
        super.write();
        super.write();
    }

    @Override
    public void onStop(){

    }
}
