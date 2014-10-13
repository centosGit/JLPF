package dk.itu.spcl.eyedroid.sdk.impl;

import dk.itu.spcl.eyedroid.sdk.core.Computable;
import dk.itu.spcl.eyedroid.sdk.core.Filter;
import dk.itu.spcl.eyedroid.sdk.core.Pipe;

import java.util.List;
import java.util.Map;

/**
 * Created by centos on 10/13/14.
 */
public class ComputableImplementation extends Computable {

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
