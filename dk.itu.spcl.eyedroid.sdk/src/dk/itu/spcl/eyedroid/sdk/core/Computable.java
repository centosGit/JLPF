package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
/**
 * Computable class defines a filters/pipes processing architecture.
 * The class uses {@link dk.itu.spcl.eyedroid.sdk.core.Filter} and {@link dk.itu.spcl.eyedroid.sdk.core.Pipe}
 * objects to define such structure.
 */
public class Computable {
    /**
     * Add a {@link dk.itu.spcl.eyedroid.sdk.core.Filter} object to the processing structure.
     * @param filter Defines a processing filter (single or composed)
     */
    public void addFilter (Filter filter){

    }
    /**
     * Remove a {@link dk.itu.spcl.eyedroid.sdk.core.Filter} object previously added.
     * @return Return the removed filter.
     */
    //TODO Check
    /*public Filter removeFilter (){

        return new FilterLeaf();
    }*/
    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the source to be processed by the filters
     * @param bundle Wrapped object to be processed.
     */
    public void pushToSource (Bundle bundle){

    }
    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} processed object from the sink.
     * @return Wrapped object containing the result.
     */
    public Bundle popFromSink (){

        return new Bundle();
    }
}
