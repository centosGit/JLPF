package dk.itu.spcl.eyedroid.sdk.impl;

import dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore;
import dk.itu.spcl.eyedroid.sdk.io.InputReader;
import dk.itu.spcl.eyedroid.sdk.io.OutputWriter;
import dk.itu.spcl.eyedroid.sdk.io.IOController;

/**
 * Created by Daniel on 10/16/2014.
 */
public class TestImplIOController extends IOController{

    public TestImplIOController(EyeDroidCore core, InputReader reader, OutputWriter writer){
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
