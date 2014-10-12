package dk.itu.spcl.eyedroid.sdk.test;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.FilterLeaf;

/**
 * Created by centos on 10/12/14.
 */
public class TestFilter extends FilterLeaf {
    @Override
    protected Bundle execute(Bundle bundle) {
        int count = (int) bundle.get(Main.COUNTER);
        System.out.println(this.getFilterName() + "   " +  count);
        bundle.put(Main.COUNTER , ++count);
        return bundle;
    }
}
