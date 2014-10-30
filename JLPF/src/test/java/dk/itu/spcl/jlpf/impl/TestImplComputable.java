package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.Computable;
import dk.itu.spcl.jlpf.core.Filter;
import dk.itu.spcl.jlpf.core.Pipe;

import java.util.List;
import java.util.Map;

public class TestImplComputable extends Computable {

    public TestImplComputable(int pipeCapacity) {
        super(pipeCapacity);
    }

    public int getComputableSize(){
        return mFilterList.size();
    }

    public boolean isConsistent(){
        return mFilterList.size() == mFilterMap.size();
    }

    public List<Filter> getList(){
        return mFilterList;
    }

    public Map<Integer , Filter> getMap(){
        return mFilterMap;
    }

    public Pipe getSource(){
        return mPipeSource;
    }

    public Pipe getSink(){
        return mPipeSink;
    }
}
