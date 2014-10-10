package dk.itu.spcl.eyedroid.sdk.core;

import dk.itu.spcl.eyedroid.sdk.common.Bundle;

/**
 * Created by centos on 10/10/14.
 */
public abstract class Filter {

    public void execute (Bundle bundle){

    }

    public void setInput (Pipe pipe){

    }

    public void setOutput (Pipe pipe){

    }

    public void pushToOutput (Bundle bundle){

    }

    public Bundle popFromInput (){

        return new Bundle();
    }

    public void run (){

    }
}
