package com.wealoha.thrift.exception;

/**
 * 
 * @author javamonk
 */
public class ConnectionFailException extends ThriftException {

    private static final long serialVersionUID = 4457437871618462115L;

    public ConnectionFailException() {
        super();
    }

    public ConnectionFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
