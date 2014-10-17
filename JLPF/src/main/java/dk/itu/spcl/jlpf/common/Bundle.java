package dk.itu.spcl.jlpf.common;

import java.util.HashMap;

/**
 * This class should be used to pass different kind of objects between
 * components in the core. For example {@link dk.itu.spcl.jlpf.io.IOController}
 * reads and writes {@link dk.itu.spcl.jlpf.common.Bundle} objects.
 * Also {@link dk.itu.spcl.jlpf.core.Filter} objects communicate by passing
 * {@link dk.itu.spcl.jlpf.common.Bundle} objects through the
 * {@link dk.itu.spcl.jlpf.core.pipes.BlockingPipe} objects.
 */
public class Bundle {

    private HashMap<String, Object> mMap;   //Map object container

    public Bundle() {
        mMap = new HashMap<String, Object>();
    }

    /**
     * Put key-value object.
     *
     * @param key   Object key
     * @param value Object value
     */
    public void put(String key, Object value) {
        mMap.put(key, value);
    }

    /**
     * Get value from specified key.
     *
     * @param key Object key
     * @return Object value
     */
    public Object get(String key) {
        return mMap.get(key);
    }
}
