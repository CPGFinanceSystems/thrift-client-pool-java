package com.wealoha.thrift.service;

import org.apache.thrift.TException;

import com.wealoha.thrift.service.TestThriftService.Iface;

/**
 * 
 * @author javamonk
 */
public class TestThriftServiceHandler implements Iface {

    @Override
    public String echo(String message) throws TException {
        return message;
    }

}
