package dk.itu.spcl.eyedroid.sdk.io;

import dk.itu.spcl.eyedroid.sdk.core.EyeDroidCore;

/**
 * This class is the main class for input/output to the core.
 * Classes that inherit should provide proper implementations for most of the methods.
 * This class is responsible for creating any threads for I/O and any different
 * protocols to be used for reading and writing.
 */
public abstract class IOController {

    public final InputReader InputReader;       //Associated bundle input reader
    public final OutputWriter OutputWriter;     //Associated bundle output writer
    private final EyeDroidCore mCore;           //EyeDroid core instance
    private boolean isRunning;                  //IO controller state

    /**
     * Constructor of the IO controller.
     *
     * @param core   EyeDroid core instance
     * @param reader Input reader instance
     * @param writer Output writer instance
     */
    public IOController(EyeDroidCore core, InputReader reader, OutputWriter writer) {
        mCore = core;
        InputReader = reader;
        OutputWriter = writer;
    }

    /**
     * Checks if the controller is currently running.
     *
     * @return Is controller started?
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Sets IOController running state.
     */
    private boolean seIsStarted(boolean is) {
        return isRunning = is;
    }

    /**
     * This method should be called only after the init method has been called.
     * It will start any threads used by the controller and then the actual
     * reading and writing will start
     */
    public void start() {
        init();
        seIsStarted(true);
        onExecute();
    }

    /**
     * Stops the threads used by the controller and closes both the {@link dk.itu.spcl.eyedroid.sdk.io.InputReader}
     * and the {@link dk.itu.spcl.eyedroid.sdk.io.OutputWriter}
     */
    public void stop() {
        onStop();
        seIsStarted(false);
    }

    /**
     * This method will restart the controller. If the controller has already started it will
     * first stop it and then start it again.
     */
    public void restart() {
        stop();
        cleanup();
        start();
    }

    /**
     * Read from input and queue bundle for processing into the core,
     */
    public void read() {
        mCore.pushBundle(InputReader.readInput());
    }

    /**
     * Pop processed bundle from core and write into output.
     */
    public void write() {
        OutputWriter.writeOutput(mCore.popBundle());
    }

    /**
     * This method should be used to initiate the controller.
     * I.e. Create any potential threads, initiate different protocols.
     */
    public abstract void init();

    /**
     * This method should should contain the main execution of the controller.
     */
    public abstract void onExecute();

    /**
     * This method should be used to stop current execution.
     */
    public abstract void onStop();

    /**
     * This method should be used to cleanup.
     * I.e. Cleanup IO protocols.
     */
    public abstract void cleanup();
}

