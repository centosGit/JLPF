package dk.itu.spcl.jlpf.core;

/**
 * Created by centos on 10/30/14.
 */
public class StatisticsThread extends Thread {

    private final int mSleepTime;
    private final ProcessingCore core;
    private final ProcessingCore.StatisticsCallback callback;
    private volatile boolean isRunning;

    public StatisticsThread(ProcessingCore core , ProcessingCore.StatisticsCallback callback , int time){
        this.core = core;
        this.callback = callback;
        mSleepTime = time;
    }

    @Override
    public void run() {
        isRunning = true;
        while(true){
               try {
                   Thread.sleep(mSleepTime);
                   callback.onStatisticsUpdates(core.getStatisticsNow());
               } catch (InterruptedException e) {
                   isRunning = false;
                   break;
               }
        }
    }

    public boolean isRunning(){
        return isRunning;
    }
}
