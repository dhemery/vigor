package com.dhemery.victor.examples.runner;

import com.dhemery.victor.frank.FrankAgent;

import java.util.Properties;

public class ConfigurableFrankAgent extends FrankAgent {
    public ConfigurableFrankAgent(Properties configuration) {
        super(domainName(configuration), port(configuration));
    }

    private static String domainName(Properties configuration) {
        return configuration.getProperty("application.host");
    }

    private static long port(Properties configuration) {
        return Long.parseLong(configuration.getProperty("frank.server.port"));
    }
}
