package com.wealoha.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;
import org.apache.thrift.TServiceClient;

import java.io.Closeable;

/**
 * The thrift client which hold the connection to backend server.<br/>
 * 
 * ThriftClient is not thread-safe, you must obtain separately from
 * {@link ThriftClientPool} for each thread.
 * 
 * @author javamonk
 */
@Slf4j
public class ThriftClient<T extends TServiceClient> implements Closeable {

    private final T client;

    private final ObjectPool<ThriftClient<T>> pool;

    private final ServiceInfo serviceInfo;

    private boolean finish;

    public ThriftClient(T client, ObjectPool<ThriftClient<T>> pool,
            ServiceInfo serviceInfo) {
        super();
        this.client = client;
        this.pool = pool;
        this.serviceInfo = serviceInfo;
    }

    /**
     * get backend service which this client current connect to
     */
    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    /**
     * Retrieve the IFace
     */
    public T iFace() {
        return client;
    }

    @Override
    public void close() {
        try {
            if (finish) {
                log.info("return object to pool: " + this);
                finish = false;
                pool.returnObject(this);
            } else {
                log.warn("not return object cause not finish {}", client);
                closeClient();
                pool.invalidateObject(this);
            }
        } catch (Exception e) {
            log.warn("return object fail, close", e);
            closeClient();
        }
    }

    void closeClient() {
        log.debug("close client {}", this);
        ThriftUtil.closeClient(this.client);
    }

    /**
     * client should return to pool
     * 
     */
    public void finish() {
        this.finish = true;
    }

    void setFinish(boolean finish) {
        this.finish = finish;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeClient();
    }
}
