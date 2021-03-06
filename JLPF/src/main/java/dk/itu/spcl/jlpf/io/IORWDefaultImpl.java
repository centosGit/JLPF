package dk.itu.spcl.jlpf.io;

import dk.itu.spcl.jlpf.common.Bundle;

import java.io.IOException;

/**
 * This class is a convenient default implementation that acts both as a
 * {@link dk.itu.spcl.jlpf.io.InputReader} and a {@link dk.itu.spcl.jlpf.io.OutputWriter}.
 * Other implementations could use different reader and writer objects.
 * The default implementation calls init and cleanup methods on IO protocols.
 */

public class IORWDefaultImpl implements InputReader, OutputWriter {

    private final IOProtocolReader protocolReader;   //Reader implementation
    private final IOProtocolWriter protocolWriter;   //Writer implementation

    /**
     * Default constructor
     * @param protocolReader Protocol reader
     * @param protocolWriter Protocol writer
     */
    public IORWDefaultImpl(IOProtocolReader protocolReader, IOProtocolWriter protocolWriter) {
        this.protocolReader = protocolReader;
        this.protocolWriter = protocolWriter;
    }

    /**
     * Initialize protocol reader
     */
    @Override
    public void initReader() throws IOException{
        protocolReader.init();
    }

    /**
     * Initialize protocol writer
     */
    @Override
    public void initWriter() throws IOException{
        protocolWriter.init();
    }

    /**
     * Read bundle from input
     *
     * @return Wrapping bundle object
     */
    @Override
    public Bundle readInput() throws IOException{
        return protocolReader.read();
    }

    /**
     * Write bundle to output
     *
     * @param bundle Wrapping bundle object
     */
    @Override
    public void writeOutput(Bundle bundle) throws IOException{
        protocolWriter.write(bundle);
    }

    /**
     * Cleanup protocol reader
     */
    @Override
    public void cleanupReader(){
        protocolReader.cleanup();
    }

    /**
     * Cleanup protocol writer
     */
    @Override
    public void cleanupWriter(){
        protocolWriter.cleanup();
    }
}
