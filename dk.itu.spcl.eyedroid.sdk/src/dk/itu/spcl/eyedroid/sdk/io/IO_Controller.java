package dk.itu.spcl.eyedroid.sdk.io;

/**
 * Created by centos on 10/10/14.
 */
public abstract class IO_Controller {

    private boolean mStarted;

    public abstract void init( InputReader reader , OutputWriter writer );

    public abstract void start();

    public abstract void stop();

    public abstract void restart();

    public boolean isStarted(){
        return mStarted;
    }
}
