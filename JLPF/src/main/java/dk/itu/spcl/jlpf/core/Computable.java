package dk.itu.spcl.jlpf.core;

import dk.itu.spcl.jlpf.common.Bundle;
import dk.itu.spcl.jlpf.core.pipes.BlockingPipe;
import dk.itu.spcl.jlpf.core.pipes.PollingPipe;
import dk.itu.spcl.jlpf.core.pipes.TimeOutPipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Computable class defines a filters/pipes processing architecture.
 * The class uses {@link dk.itu.spcl.jlpf.core.Filter} and {@link dk.itu.spcl.jlpf.core.pipes.BlockingPipe}
 * objects to define such structure.
 */
public class Computable {

    protected List<Filter> mFilterList;                   //Filter list
    protected HashMap<Integer, Filter> mFilterMap;        //Map mFilterList replica

    protected Pipe mPipeSource;                           //Input pipe
    protected Pipe mPipeSink;                             //Output pipe

    public Computable() {
        mFilterList = new ArrayList<Filter>();
        mFilterMap = new HashMap<Integer, Filter>();
        mPipeSink = new BlockingPipe();
        mPipeSource = new BlockingPipe();
    }

    /**
     * Get an unmodifiable reference to the filter list.
     *
     * @return Unmodifiable filter list
     */
    public List<Filter> getFilterList() {
        return Collections.unmodifiableList(mFilterList);
    }

    /**
     * Add a {@link dk.itu.spcl.jlpf.core.Filter} object to the processing structure.
     *
     * @param filter Defines a processing filter (single or composed)
     * @return Return true if filter added
     */
    public boolean addFilter(Filter filter) {
        if (mFilterMap.containsKey(filter.getFilterId()))
            return false;
        mFilterList.add(filter);
        mFilterMap.put(filter.getFilterId(), filter);
        return true;
    }

    /**
     * Remove a {@link dk.itu.spcl.jlpf.core.Filter} object previously added.
     *
     * @param id Filter id
     * @return Return the removed filter
     */
    public Filter removeFilter(int id) {
        if (mFilterMap.containsKey(id)) {
            Filter filter = mFilterMap.remove(id);
            mFilterList.remove(filter);
            return filter;
        }
        return null;
    }

    /**
     * Push a {@link dk.itu.spcl.jlpf.common.Bundle} object into the source to be processed by the filters
     *
     * @param bundle Wrapped object to be processed
     */
    public void pushToSource(Bundle bundle) {
        mPipeSource.push(bundle);
    }

    /**
     * Pop a {@link dk.itu.spcl.jlpf.common.Bundle} processed object from the sink.
     *
     * @return Wrapped object containing the result
     */
    public Bundle popFromSink() {
        return mPipeSink.pop();
    }

    /**
     * Clear all {@link BlockingPipe} objects connecting {@link dk.itu.spcl.jlpf.core.Filter}
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
     * Different pipe types are used in order to optimize the execution according to the number of threads to be used.
     *
     * @param numberOfThreads Number of threads to be used to execute the filters
     */
    public void setupFilterPipes(int numberOfThreads) {

        mFilterList.get(0).setInput(mPipeSource);
        mFilterList.get(mFilterList.size() - 1).setOutput(mPipeSink);
        for (int i = 0; i < mFilterList.size() - 1; i++) {

            Pipe pipe;
            if (numberOfThreads == 1) {
                pipe = new PollingPipe();
            } else if (numberOfThreads == mFilterList.size()) {
                pipe = new BlockingPipe();
            } else {
                pipe = new TimeOutPipe(10, TimeUnit.MILLISECONDS);
            }
            mFilterList.get(i).setOutput(pipe);
            mFilterList.get(i + 1).setInput(pipe);
        }
    }
}
