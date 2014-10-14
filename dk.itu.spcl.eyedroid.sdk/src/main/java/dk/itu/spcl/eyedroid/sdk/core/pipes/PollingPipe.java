package dk.itu.spcl.eyedroid.sdk.core.pipes;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.Pipe;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by centos on 10/14/14.
 */
public class PollingPipe extends Pipe {

    public PollingPipe(){
        mInternalQueue = new LinkedBlockingDeque<Bundle>();
    }

    @Override
    public void push(Bundle bundle) {
        mInternalQueue.offer(bundle);
    }

    @Override
    public Bundle pop() {
        return mInternalQueue.poll();
    }
}
