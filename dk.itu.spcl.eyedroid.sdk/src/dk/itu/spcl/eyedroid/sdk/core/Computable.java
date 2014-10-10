package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Created by Daniel on 10/10/2014.
 */
public class Computable {

    public void addFilter (Filter filter){

    }

    public Filter removeFilter (){

        return new FilterLeaf();
    }

    public void pushToSource (Bundle bundle){

    }

    public Bundle popFromSink (){

        return new Bundle();
    }

}
