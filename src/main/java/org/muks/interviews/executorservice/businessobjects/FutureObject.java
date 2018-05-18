package org.muks.interviews.executorservice.businessobjects;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureObject {
    private static Logger LOG = LoggerFactory.getLogger(FutureObject.class);

    private String NAME = null;
    private boolean IS_RUNNING = false;
    private Object RESULTS = null;
    private int SLEEP_TIME = 5000;
    private int RETRIES = 180; /** leads to 15 min's with a sleep time of 5000*/
    private Thread THREAD_OBJECT;

    public FutureObject() {}

    public FutureObject(String name) {
        this.NAME = name;
    }

    public String getName() { return this.NAME; }

    public void setThreadObject(Thread t) {
        this.THREAD_OBJECT = t;
    }

    public Thread getThreadObject() {
        return this.THREAD_OBJECT;
    }

    public void execute() {
        this.THREAD_OBJECT.start();
    }

    /**
     * Setting user defined wait and sleep time so that the executor can wait
     * @param sleepTime - time to wait
     * @param retries   - total no. of retries which defines the total time to wait
     */
    public void setWaitTimeouts(int sleepTime, int retries) {
        this.SLEEP_TIME = sleepTime;
        this.RETRIES = retries;
    }


    /**
     * Implementation log:
     *      - if IS_RUNNING == true
     *          -> run into the method notifyWhenDone();
     *
     *          => returns true | false
     *              if true, the successfully completed and the RESULTS object is returned
     *              if false, its not successfully and it can't wait beyond configured wait time and hence returns null
     *                  Note: Multiple implementations could be done here
     *                      1. Wait for a definitely period
     *                          - Either go by hardcoding wait timeout for 15 min's or
     *                          - and also expose methods to set the timeout values.
     *
     *                      2. Wait for indefinite period
     *
     *      - irrespective of successfully completed or not, return the RESULTS object.
     *          - if not completed successfully within 15 min's, then returns NULL else some object after a wait time
     */
    public Object get() throws InterruptedException {
        if (IS_RUNNING)
             notifyWhenDone();

        return this.RESULTS;
    }


    /**
     * private internal method, notify when thread is completed
     * @return - boolean of the same value as IS_RUNNING
     * @throws InterruptedException - thread sleep interruption
     */
    private boolean notifyWhenDone() throws InterruptedException {
        boolean done = false;

        while (this.THREAD_OBJECT.isAlive()) {
            this.RETRIES--;
            Thread.sleep(this.SLEEP_TIME);

//            if (this.RETRIES == 0)
//                return this.THREAD_OBJECT.isAlive();       /** It could be the case that the job would have also completed by now */
        }

        return true;
    }

}
