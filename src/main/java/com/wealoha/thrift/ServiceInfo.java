package com.wealoha.thrift;

import lombok.Value;

/**
 * @author javamonk
 */
@Value
public class ServiceInfo {

    private final String host;

    private final int port;
}
