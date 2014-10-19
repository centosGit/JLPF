package dk.itu.spcl.jlpf.io;

import dk.itu.spcl.jlpf.core.ProcessingCore;

import java.io.IOException;

/**
 * This class is the main class for input/output to the core.
 * Classes that inherit should provide proper implementations for most of the methods.
 * This class is responsible for creating any threads for I/O and any different
 * protocols to be used for reading and writing.
 */
public abstract class IOController {

    protected final InputReader mInputReader;       //Associated bundle input reader
    protected final OutputWriter mOutputWriter;     //Associated bundle output writer
    protected final ProcessingCore mCore;          //ProcessingCore  instance
    private boolean isRunning;                     //IO controller state

    /**
     * Constructor of the IO controller.
     *
     * @param core   ProcessingCore  instance
     * @param reader Input reader instance
     * @param writer Output writer instance
     */
    public IOController(ProcessingCore core, InputReader reader, OutputWriter writer) {
        mCore = core;
        mInputReader = reader;
        mOutputWriter = writer;
    }

    /**
     * Get a reference to input reader
     *
     * @return Input reader
     */
    public InputReader getInputReader() {
        return mInputReader;
    }

    /**
     * Get a reference to output writer
     *
     * @return Output writer
     */
    public OutputWriter getOutputWriter() {
        return mOutputWriter;
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
    private boolean setIsStarted(boolean is) {
        return isRunning = is;
    }

    /**
     * This method should be called only after the init method has been called.
     * It will start any threads used by the controller and then the actual
     * reading and writing will start
     */
    public void start() {
        if (!isRunning) {
            setupController();
            setIsStarted(true);
            onExecute();
        }
    }

    /**
     * Stops the threads used by the controller and closes both the {@link dk.itu.spcl.jlpf.io.InputReader}
     * and the {@link dk.itu.spcl.jlpf.io.OutputWriter}
     */
    public void stop() {
        if (isRunning) {
            onStop();
            cleanupIO();
            setIsStarted(false);
        }
    }

    /**
     * This method will restart the controller. If the controller has already started it will
     * first stop, cleanup and then start it again.
     */
    public void restart() {
        stop();
        start();
    }

    /**
     * Called after Stop method. Cleans up IO protocols.
     */
    private void cleanupIO() {
        mInputReader.cleanupReader();
        mOutputWriter.cleanupWriter();
    }

    /**
     * Read from input and queue bundle for processing into the core,
     */
    public void read() throws IOException {
        mCore.pushBundle(mInputReader.readInput());
    }

    /**
     * Pop processed bundle from core and write into output.
     */
    public void write() throws IOException {
        mOutputWriter.writeOutput(mCore.popBundle());
    }

    /**
     * This method should be used to initiate the controller.
     * I.e. Create any potential threads, initiate different protocols.
     */
    public abstract void setupController();

    /**
     * This method should should contain the main execution of the controller.
     */
    protected abstract void onExecute();

    /**
     * This method should be used to stop current execution.
     */
    protected abstract void onStop();
}

