package com.dhemery.victor.examples.runner;

import java.util.Properties;

public class ConfigurableFrankAgent extends MyFrankAgent {
    public ConfigurableFrankAgent(Properties configuration) {
        super(domainName(configuration), port(configuration));
    }

    private static String domainName(Properties configuration) {
        return configuration.getProperty("simulator.hostname");
    }

    private static long port(Properties configuration) {
        return Long.parseLong(configuration.getProperty("frank.server.port"));
    }
}
