package dk.itu.spcl.eyedroid.sdk.impl;

import dk.itu.spcl.eyedroid.sdk.core.Filter;
import dk.itu.spcl.eyedroid.sdk.core.FilterComposite;

import java.util.HashMap;
import java.util.List;

public class CompositeImplementation extends FilterComposite {

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
