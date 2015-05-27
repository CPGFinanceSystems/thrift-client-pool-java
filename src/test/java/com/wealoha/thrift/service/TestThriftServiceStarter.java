package com.wealoha.thrift.service;

import com.wealoha.thrift.service.TestThriftService.Processor;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Simple echo thrift service for testing purpose.
 *
 * @author javamonk
 */
public class TestThriftServiceStarter {

    public static void main(String[] args) {
        int port = 9090;

        try {
            TServerTransport serverTransport = new TServerSocket(port);

            Args processor = new TThreadPoolServer.Args(serverTransport)
                    .inputTransportFactory(new TFramedTransport.Factory())
                    .outputTransportFactory(new TFramedTransport.Factory())
                    .processor(new Processor<>(new TestThriftServiceHandler()));
            //            processor.maxWorkerThreads = 20;
            TThreadPoolServer server = new TThreadPoolServer(processor);

            System.out.println("Starting the server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
