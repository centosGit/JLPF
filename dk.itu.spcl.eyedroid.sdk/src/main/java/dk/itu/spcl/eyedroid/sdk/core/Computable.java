package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Computable class defines a filters/pipes processing architecture.
 * The class uses {@link dk.itu.spcl.eyedroid.sdk.core.Filter} and {@link dk.itu.spcl.eyedroid.sdk.core.Pipe}
 * objects to define such structure.
 */
public class Computable {

    private List<Filter> mFilterList;                   //Filter list
    private HashMap<Integer, Filter> mFilterMap;        //Map mFilterList replica

    private Pipe mPipeSource;                           //Input pipe
    private Pipe mPipeSink;                             //Output pipe

    public Computable() {
        mFilterList = new ArrayList<Filter>();
        mFilterMap = new HashMap<Integer, Filter>();
        mPipeSource = new Pipe();
        mPipeSink = new Pipe();
    }

    /**
     * Get an unmodifiable reference to the filter list.
     *
     * @return Unmodifiable filter list
     */
    public List<Filter> getFilterList() {
        setupFilterPipes();
        return Collections.unmodifiableList(mFilterList);
    }

    /**
     * Add a {@link dk.itu.spcl.eyedroid.sdk.core.Filter} object to the processing structure.
     *
     * @param filter Defines a processing filter (single or composed)
     */
    public boolean addFilter(Filter filter) {
        if (mFilterMap.containsKey(filter.getFilterId()))
            return false;
        mFilterList.add(filter);
        mFilterMap.put(filter.getFilterId(), filter);
        return true;
    }

    /**
     * Remove a {@link dk.itu.spcl.eyedroid.sdk.core.Filter} object previously added.
     *
     * @return Return the removed filter
     */
    public boolean removeFilter(int id) {
        if (mFilterMap.containsKey(id)) {
            Filter filter = mFilterMap.get(id);
            mFilterList.remove(filter);
            return true;
        }
        return false;
    }

    /**
     * Push a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} object into the source to be processed by the filters
     *
     * @param bundle Wrapped object to be processed
     */
    public void pushToSource(Bundle bundle) {
        mPipeSource.push(bundle);
    }

    /**
     * Pop a {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} processed object from the sink.
     *
     * @return Wrapped object containing the result
     */
    public Bundle popFromSink() {
        return mPipeSink.pop();
    }

    /**
     * Clear all {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} objects connecting {@link dk.itu.spcl.eyedroid.sdk.core.Filter}
     * object filters.
     */
    public void cleanup() {
        mPipeSink.cleanup();
        for (Filter filter : mFilterList)
            filter.getInputPipe().cleanup();
    }

    /**
     * Connect added filters by using pipes.
     * First filter is connected to the core input source.
     * Last filter is connected to the core output sink.
     */
    private void setupFilterPipes() {
        mFilterList.get(0).setInput(mPipeSource);
        mFilterList.get(mFilterList.size() - 1).setOutput(mPipeSink);
        for (int i = 0; i < mFilterList.size() - 1; i++) {
            Pipe pipe = new Pipe();
            mFilterList.get(i).setOutput(pipe);
            mFilterList.get(i + 1).setInput(pipe);
        }
    }
}
