package com.dhemery.victor.examples.runner;

import com.dhemery.configuring.Configuration;
import com.dhemery.configuring.LoadProperties;
import com.dhemery.configuring.MapBackedConfiguration;
import com.dhemery.expressing.AssistedExpressive;
import com.dhemery.expressing.PollingAssistant;
import com.dhemery.expressing.PublishingPollingAssistant;
import com.dhemery.publishing.Channel;
import com.dhemery.publishing.MethodSubscriptionChannel;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.Victor;
import org.junit.After;
import org.junit.Before;

import static com.dhemery.victor.build.FrankVictorBuilder.victor;
import static com.dhemery.victor.examples.application.ApplicationQueries.isRunning;

public class OnVigorApp extends AssistedExpressive {
    private static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    private static final Configuration CONFIGURATION = configuration();
    private static final Channel CHANNEL = channel();

    private IosApplication application;
    private IosDevice device;

    public OnVigorApp() {
        super(assistant());
    }

    @Before
    public void startDevice() {
        String bundlePath = CONFIGURATION.requiredOption("victor.application.bundle.path");
        Victor victor = victor(bundlePath)
                .withPublisher(CHANNEL)
                .build();
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
        return new PublishingPollingAssistant(pollingTimeout, pollingInterval, CHANNEL);
    }

    private static long propertyAsLong(String propertyName) {
        String timeoutString = CONFIGURATION.requiredOption(propertyName);
        return Long.parseLong(timeoutString);
    }
}
