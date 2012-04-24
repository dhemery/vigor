package com.dhemery.victor.examples.runner;

import com.dhemery.victor.frank.FrankAgent;

import java.util.Properties;

public class CreateFrankAgent {
    /**
     * Create a Frank agent that interacts with a Frank server
     * at the default {@link FrankAgent#DEFAULT_FRANK_SERVER_DOMAIN_NAME host}
     * and {@link FrankAgent#DEFAULT_FRANK_SERVER_PORT port}.
     * @return the Frank agent.
     */
    public static FrankAgent forDefaultFrankServerUrl() {
        return forFrankServerUrl(FrankAgent.DEFAULT_FRANK_SERVER_DOMAIN_NAME,
                FrankAgent.DEFAULT_FRANK_SERVER_PORT);
    }

    /**
     * Creates a Frank agent that interacts with the Frank server
     * at the given URL.
     * @param url the URL at which the Frank server listens.
     * @return the Frank agent.
     */
    public static FrankAgent forFrankServerUrl(String url) {
        return new FrankAgent(url);
    }

    /**
     * Create a Frank agent that interacts with the Frank server
     * at the given HTTP host and port.
     * The host name must not include the HTTP scheme (e.g. "http://").
     * @param host the name of the Frank server's host.
     * @param port the port on which the Frank server listens.
     * @return the Frank agent.
     */
    public static FrankAgent forFrankServerUrl(String host, Long port) {
        return forFrankServerUrl(makeUrl(host, port));
    }

    /**
     * <p>Create a Frank agent that interacts with the Frank server
     * at designated by property values.
     *</p>
     * <p>
     * The host name is specified by the property {@code application.host}.
     * The host name must not include the HTTP scheme (e.g. "http://").
     *</p>
     * <p>
     * The port number is specified by the property {@code frank.server.port}.
     * The port must be parseable by {@link Long#parseLong(String)}.
     *</p>
     * @param properties that specify the host and port at which the Frank server listens
     * @return the Frank agent.
     */
    public static FrankAgent fromProperties(Properties properties) {
        return forFrankServerUrl(makeUrl(hostProperty(properties), portProperty(properties)));
    }

    private static String hostProperty(Properties properties) {
        return properties.getProperty("application.host");
    }

    private static String makeUrl(String host, Long port) {
        return String.format("http://%s:%s", host, port);
    }

    private static Long portProperty(Properties properties) {
        return Long.parseLong(properties.getProperty("frank.server.port"));
    }
}
