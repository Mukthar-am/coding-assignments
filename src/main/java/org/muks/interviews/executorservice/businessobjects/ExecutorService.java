package org.muks.interviews.executorservice.businessobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutorService {
    private static Logger LOG = LoggerFactory.getLogger(FutureObject.class);
    private FutureObject[] POOL;

    private static ExecutorService instance = new ExecutorService();

    /**
     * Fixed length pool
     * @param poolSize
     */
    public static ExecutorService newFixedLengthPool(int poolSize) {
        instance.POOL = new FutureObject[poolSize];
        instance.init();
        return instance;
    }

    private void init() {
        for (int i = 0; i < this.POOL.length; i++) {
            POOL[i] = new FutureObject("Thread-" + i);
        }

        LOG.info("Size of the executor-service pool:= {}", this.POOL);
        printPoolObjectNames();
    }

    public void printPoolObjectNames() {
        LOG.info("");
        LOG.info("= Pool containers are initialized as below; =");
        for (int i = 0; i < this.POOL.length; i++) {
            LOG.info("Container name:= " + this.POOL[i].getName());
        }
        LOG.info("");
    }


    public void submit(Thread thread) throws PoolFullException {
        FutureObject availableFutureObject;

        if ((availableFutureObject = getNextAvailableContainerFromPool()) != null) {
            availableFutureObject.setThreadObject(thread);
            LOG.info("Submission accepted/successful.");

            availableFutureObject.execute();
        } else {
            LOG.info("Pool full exception.");
            throw new PoolFullException("No pool containers avaialble, please try after sometime.");
        }

    }

    private FutureObject getNextAvailableContainerFromPool() {
        for (int i = 0; i < this.POOL.length; i++) {
            if (this.POOL[i].getThreadObject() == null)
                return this.POOL[i];
        }
        return null;
    }

    public FutureObject[] getFutureList() {
        return this.POOL;
    }
}
