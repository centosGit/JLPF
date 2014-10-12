package dk.itu.spcl.eyedroid.sdk.common;

import java.util.HashMap;

/**
 * This class should be used to pass different kind of objects between
 * components in the core. For example {@link dk.itu.spcl.eyedroid.sdk.io.IO_Controller}
 * reads and writes {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} objects.
 * Also {@link dk.itu.spcl.eyedroid.sdk.core.Filter} objects communicate by passing
 * {@link dk.itu.spcl.eyedroid.sdk.common.Bundle} objects through the
 * {@link dk.itu.spcl.eyedroid.sdk.core.Pipe} objects.
 */
public class Bundle {

    private HashMap<String , Object> mMap;

    public Bundle(){
        mMap = new HashMap<String, Object>();
    }

    public void put( String key , Object value ){
        mMap.put(key , value);
    }

    public Object get(String key){
        return mMap.get(key);
    }
}
