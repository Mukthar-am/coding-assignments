package org.muks.interviews.executorservice.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: DoSomething is a random runnable thread for demo purpose/acting stub
 */
public class DoSomething implements Runnable {
    private Logger LOG = LoggerFactory.getLogger(DoSomething.class);
    private String NAME = null;

    public DoSomething(String name) {
        this.NAME = name;
    }

    @Override
    public void run() {
        LOG.info("Name:- \"{}\", was asked to do something and I did print this.", this.NAME);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            LOG.error("Thread InterruptedException");
        }
    }
}
