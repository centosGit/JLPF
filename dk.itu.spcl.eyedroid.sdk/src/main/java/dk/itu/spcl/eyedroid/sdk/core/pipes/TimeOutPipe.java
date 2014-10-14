package dk.itu.spcl.eyedroid.sdk.core.pipes;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;
import dk.itu.spcl.eyedroid.sdk.core.Pipe;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by centos on 10/14/14.
 */
public class TimeOutPipe extends Pipe {

    private int mTimeOut;
    private TimeUnit mTimeUnit;
    public TimeOutPipe(int timeOut , TimeUnit timeUnit){
        mInternalQueue = new LinkedBlockingQueue<Bundle>();
        mTimeOut = timeOut;
        mTimeUnit = timeUnit;
    }

    @Override
    public void push(Bundle bundle) {
        try {
            mInternalQueue.offer(bundle , mTimeOut, mTimeUnit);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public Bundle pop() {
        try {
            return mInternalQueue.poll(mTimeOut , mTimeUnit);
        } catch (InterruptedException e) {
            return null;
        }
    }
}
