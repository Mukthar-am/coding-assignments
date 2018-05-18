package org.muks.interviews.executorservice;

import org.muks.interviews.executorservice.businessobjects.ExecutorService;
import org.muks.interviews.executorservice.businessobjects.FutureObject;
import org.muks.interviews.executorservice.businessobjects.PoolFullException;
import org.muks.interviews.executorservice.threads.DoSomething;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExecutorServiceTests {
    private Logger LOG = LoggerFactory.getLogger(ExecutorServiceTests.class);


    @Test
    public void TestPoolsize() {
        ExecutorService executorService = ExecutorService.newFixedLengthPool(5);

        Thread t1 = new Thread(new DoSomething("first thread"));
        try {
            executorService.submit(t1);

        } catch (Exception e) {
            LOG.info("Exception:- ", e);
        }

        int poolSize = executorService.getFutureList().length;
        int expectedSize = 5;
        LOG.info("CurrentSize:- {}, ExpectedSize:- {}", poolSize, expectedSize);

        Assert.assertEquals(poolSize, expectedSize);
    }


    @Test(expectedExceptions = {PoolFullException.class})
    public void TestExceptionWithOversizedInserts() throws PoolFullException {
        ExecutorService executorService = ExecutorService.newFixedLengthPool(2);

        Thread t1 = new Thread(new DoSomething("First-thread"));
        Thread t2 = new Thread(new DoSomething("Second-thread"));
        Thread t3 = new Thread(new DoSomething("Third-thread"));


        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3); /** This throws PoolFullException and is tested using TestNG annotation */
    }

    @Test
    public void TestPoolBasedExecution() {
        ExecutorService executorService = ExecutorService.newFixedLengthPool(2);

        Thread t1 = new Thread(new DoSomething("First-thread"));
        Thread t2 = new Thread(new DoSomething("Second-thread"));
        Thread t3 = new Thread(new DoSomething("Third-thread"));

        try {
            executorService.submit(t1);
            executorService.submit(t2);

            FutureObject[] futureList = executorService.getFutureList();
            for (FutureObject futureObject : futureList) {
                LOG.info("Status: " + futureObject.get());
            }

        } catch (Exception e) {
            LOG.info("Exception:- ", e);
        }

    }
}
