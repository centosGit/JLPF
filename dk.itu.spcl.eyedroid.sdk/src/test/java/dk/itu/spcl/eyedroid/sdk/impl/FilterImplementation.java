package dk.itu.spcl.eyedroid.sdk.impl;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.Filter;

public class FilterImplementation extends Filter {

    public static final String MESSAGE = "message";

    @Override
    protected Bundle execute(Bundle bundle) {
        StringBuilder builder = new StringBuilder();
        builder.append((String)bundle.get(FilterImplementation.MESSAGE)).append(getFilterName());
        bundle.put(FilterImplementation.MESSAGE , builder.toString());
        return bundle;
    }
}
