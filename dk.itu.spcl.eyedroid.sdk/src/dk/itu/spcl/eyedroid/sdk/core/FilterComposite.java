package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Abstract class that defines the behavior of a {@link dk.itu.spcl.eyedroid.sdk.core.Filter}
 * composition (composite pattern).
 */
public  class FilterComposite extends Filter {

    private LinkedList<Filter> mFilterChildren;
    private HashMap<Integer, Filter> mFilterMap;

    public FilterComposite() {
        mFilterChildren = new LinkedList<Filter>();
        mFilterMap = new HashMap<Integer, Filter>();
    }

    public boolean addFilter(Filter filter) {
        if (!hasStarted()) {
            if (mFilterMap.containsKey(filter.getFilterId()))
                return false;
            mFilterMap.put(filter.getFilterId(), filter);
            mFilterChildren.add(filter);

            return true;
        }
        return false;
    }

    public void removeFilter(int id) {
        if (!hasStarted()) {
            if (mFilterMap.containsKey(id)) {
                Filter filter = mFilterMap.remove(id);
                mFilterChildren.remove(filter);
            }
        }
    }


    /*prevent from overriding this method.  * */
    @Override
    final protected Bundle execute(Bundle bundle) {
        setStarted();
        if (bundle != null) {
            for (Filter filter : mFilterChildren) {
                bundle = filter.execute(bundle);
            }

            if (bundle != null)
                return bundle;
        }
        return null;
    }

}
