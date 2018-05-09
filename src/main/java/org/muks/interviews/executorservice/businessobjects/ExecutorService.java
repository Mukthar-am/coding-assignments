package org.muks.interviews.executorservice.businessobjects;

import java.util.ArrayList;
import java.util.List;

public class ExecutorService {
    private FutureObject[] POOL;

    private static ExecutorService instance = new ExecutorService();

    /**
     * Fixed length pool
     * @param poolSize
     */
    public void newFixedLengthPool(int poolSize) {
        this.POOL = new FutureObject[poolSize];
    }



    public void

}
