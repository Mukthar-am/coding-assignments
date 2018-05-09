package org.muks.interviews.executorservice.businessobjects;

public class PoolFullException extends Exception {
    public PoolFullException(String message) {
        super(message);
    }

}