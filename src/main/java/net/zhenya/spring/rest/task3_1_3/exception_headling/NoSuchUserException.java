package net.zhenya.spring.rest.task3_1_3.exception_headling;


public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException() {
    }

    public NoSuchUserException(String message) {
        super(message);
    }
}
