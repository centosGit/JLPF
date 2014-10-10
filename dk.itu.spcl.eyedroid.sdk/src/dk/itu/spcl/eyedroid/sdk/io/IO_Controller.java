package dk.itu.spcl.eyedroid.sdk.io;

/**
 * This class is the main class for inpup/ouput to the core.
 * Classes that inherit should provide proper implementations for most of the methods.
 * This class is responsible for creating any threads for I/O and any different
 * protocols to be used for reading and writing.
 */
public abstract class IO_Controller {

    private boolean mStarted;

    /**
     * This method should be used to initiate the Controller. Create any potential
     * threads initiate different protocols and set the controller in a state
     * that is ready to be used.
     *
     * @param reader
     * @param writer
     * */
    public abstract void init( InputReader reader , OutputWriter writer );

    /**
     * This method should be called only after the init method has been called.
     * It will start any threads used by the controller and then the actual
     * reading and writing will start
     * */
    public abstract void start();

    /**
     * Stops the threads used by the controller and closes both the {@link dk.itu.spcl.eyedroid.sdk.io.InputReader}
     * and the {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}
     * */
    public abstract void stop();


    /**
     * This method will restart the controller. If the controller has already started it will
     * first stop it and then start it again.
     * */
    public abstract void restart();

    /**
     * Checkes if the controller is currently running.
     * */
    public boolean isStarted(){
        return mStarted;
    }
}
