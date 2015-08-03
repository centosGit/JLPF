package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.ProcessingCore;
import dk.itu.spcl.jlpf.io.InputReader;
import dk.itu.spcl.jlpf.io.OutputWriter;
import dk.itu.spcl.jlpf.io.IOController;

import java.io.IOException;

public class TestImplIOController extends IOController{

    public TestImplIOController(ProcessingCore core, InputReader reader, OutputWriter writer){
        super(core,reader,writer);
    }

    @Override
    public void setupController(){

    }

    @Override
    public void onExecute(){
        try{
            super.read();
            super.read();
            super.read();
            super.read();
            super.write();
            super.write();
            super.write();
            super.write();
        }catch (IOException e){

        }
    }

    @Override
    public void onStop(){

    }
}
