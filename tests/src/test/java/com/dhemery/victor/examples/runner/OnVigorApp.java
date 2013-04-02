package com.dhemery.victor.examples.runner;

import com.dhemery.configuring.Configuration;
import com.dhemery.configuring.LoadProperties;
import com.dhemery.configuring.MapBackedConfiguration;
import com.dhemery.core.Condition;
import com.dhemery.expressing.Expressive;
import com.dhemery.polling.Ticker;
import com.dhemery.publishing.Channel;
import com.dhemery.publishing.MethodSubscriptionChannel;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.Victor;
import com.dhemery.victor.examples.polling.PollingAssistant;
import org.junit.After;
import org.junit.Before;

import static com.dhemery.victor.examples.application.ApplicationQueries.isRunning;

public class OnVigorApp extends Expressive {
    private static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    private static final Configuration CONFIGURATION = configuration();
    private static final Channel CHANNEL = channel();
    private static final PollingAssistant assistant = assistant();

    private IosApplication application;
    private IosDevice device;

    @Before
    public void startDevice() {
        Victor victor = new Victor(CONFIGURATION, CHANNEL);
        device = victor.device();
        application = victor.application();
        device().start();
        waitUntil(application(), isRunning());
    }

    @After
    public void stopDevice() {
        if(device == null) return;
        device.stop();
        device = null;
    }

    protected IosApplication application() {
        return application;
    }

    protected IosDevice device() {
        return device;
    }

    protected PollingAssistant pollingAssistant() {
        return assistant;
    }

    @Override
    public Ticker createDefaultTicker() {
        return assistant.createDefaultTicker();
    }

    @Override
    public Condition prepareToPoll(Condition condition) {
        return assistant.prepareToPoll(condition);
    }

    private static Channel channel() {
        Channel channel = new MethodSubscriptionChannel();
        channel.subscribe(new VigorCommandLogger());
        channel.subscribe(new VigorFrankLogger());
        channel.subscribe(new VigorPollLogger());
        channel.subscribe(new VigorHttpLogger());
        return channel;
    }

    private static Configuration configuration() {
        Configuration configuration = new MapBackedConfiguration();
        LoadProperties.fromFiles(VIGOR_PROPERTIES_FILES).into(configuration);
        return configuration;
    }

    private static PollingAssistant assistant() {
        long pollingTimeout = propertyAsLong("polling.timeout");
        long pollingInterval = propertyAsLong("polling.interval");
        return new PollingAssistant(CHANNEL, pollingTimeout, pollingInterval);
    }


    private static long propertyAsLong(String propertyName) {
        String timeoutString = CONFIGURATION.requiredOption(propertyName);
        return Long.parseLong(timeoutString);
    }
}
