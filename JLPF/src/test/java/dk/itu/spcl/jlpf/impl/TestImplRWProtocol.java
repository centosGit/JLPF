package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.io.IOProtocolReader;
import dk.itu.spcl.jlpf.io.IOProtocolWriter;

/**
 * Created by Daniel on 10/16/2014.
 */
public class TestImplRWProtocol implements IOProtocolReader, IOProtocolWriter{

    public int countRead;
    public int countWrite;

    @Override
    public void init(){
        countRead = 0;
        countWrite = 0;
    }

    @Override
    public Bundle read(){
        countRead ++;
        return new Bundle();
    }

    @Override
    public void write(Bundle bundle){
        countWrite ++;
    }

    @Override
    public void cleanup(){
        init();
    }
}
