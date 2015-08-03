package dk.itu.spcl.jlpf.core;

/**
 * Created by centos on 10/30/14.
 */
public class CoreStatistics {

    public final int pipeSizes[];

    public final double filterExecutionTimes[];

    public final int filterExecutionCounter[];

    public final int pipesCount;

    public final int filtersCount;

    public final String[] filterNames;

    public final int pipeCapacity;


    public CoreStatistics(int pipes , int filters , int pipeCapacity){
        pipesCount = pipes;
        filtersCount = filters;
        this.pipeCapacity = pipeCapacity;

        pipeSizes = new int[pipesCount];
        filterExecutionCounter = new int[filtersCount];
        filterExecutionTimes = new double[filtersCount];
        filterNames = new String[filtersCount];
    }

    public void addPipeSize(int position, int size){
        pipeSizes[position] = size;
    }

    public void addFilterAvgTime( int position , double time ){
        filterExecutionTimes[position] = time;
    }

    public void addFilterExecutionCounter( int position , int count ){
        filterExecutionCounter[position] = count;
    }

    public void addFilterName(int position , String name){
        filterNames[position] = name;
    }

}
