package com.wealoha.thrift.service;

import com.wealoha.thrift.service.TestThriftService.Iface;
import org.apache.thrift.TException;

/**
 * @author javamonk
 */
public class TestThriftServiceHandler implements Iface {

    @Override
    public String echo(String message) throws TException {
        return message;
    }

}
