package dk.itu.spcl.jlpf.impl;

import dk.itu.spcl.jlpf.core.Filter;
import dk.itu.spcl.jlpf.core.FilterComposite;

import java.util.HashMap;
import java.util.List;

public class TestImplComposite extends FilterComposite {

    public int compositeSize(){
        return mFilterChildren.size();
    }

    public List<Filter> getList(){
        return mFilterChildren;
    }

    public HashMap<Integer , Filter> getMap(){
        return mFilterMap;
    }
}
