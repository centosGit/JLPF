package dk.itu.spcl.jlpf.io;

import dk.itu.spcl.jlpf.common.Bundle;

import java.io.IOException;

/**
 * Interface used to implement a specific writer. Implementations of this interface
 * do not perform the actual writing but the task is delegated to
 * {@link dk.itu.spcl.jlpf.io.IOProtocolWriter}. Clients of this class should first set a proper
 * {@link dk.itu.spcl.jlpf.io.IOProtocolWriter} instance and then use the
 * {@link dk.itu.spcl.jlpf.io.OutputWriter#writeOutput(dk.itu.spcl.jlpf.common.Bundle)} to write the next
 * processed {@link dk.itu.spcl.jlpf.common.Bundle} to the output.
 */
public interface OutputWriter {

    /**
     * Initialize output writer
     */
    public void initWriter() throws IOException;

    /**
     * Method used to write the next processed {@link dk.itu.spcl.jlpf.common.Bundle}
     *
     * @param {@link dk.itu.spcl.jlpf.common.Bundle} the next processed Bundle
     */
    public void writeOutput(Bundle bundle) throws IOException;

    /**
     * Cleanup writer
     */
    public void cleanupWriter();
}
