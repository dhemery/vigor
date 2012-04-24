package com.dhemery.victor.examples.runner;

import com.dhemery.victor.frank.FrankAgent;

public class MyFrankAgent extends FrankAgent {
    public static final String DEFAULT_FRANK_SERVER_DOMAIN_NAME = "localhost";
    public static final Long DEFAULT_FRANK_SERVER_PORT = 37265L;

    /**
     * Prepares to attach a Frank viewAgent
     * to the Frank server at the default domain name and port.
     */
    public MyFrankAgent() {
        this(DEFAULT_FRANK_SERVER_DOMAIN_NAME, DEFAULT_FRANK_SERVER_PORT);
    }

    /**
     * Prepares to attach a Frank viewAgent to the Frank server at a domain and port.
     * @param domainName the Frank server's domain.
     * @param port the port at which the Frank server listens.
     */
    public MyFrankAgent(String domainName, Long port) {
        super(urlFor(domainName, port));
    }

    private static String urlFor(String host, long port) {
        return String.format("http://%s:%s", host, port);
    }
}
