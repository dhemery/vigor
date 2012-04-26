package com.dhemery.victor.examples.runner;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.device.CreateIosDevice;
import com.dhemery.victor.device.IosDeviceConfiguration;
import com.dhemery.victor.examples.extensions.ApplicationOrientationQuery;
import com.dhemery.victor.frank.CreateFrankAgent;
import com.dhemery.victor.frank.FrankAgent;
import com.dhemery.victor.frank.FrankIosApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static com.dhemery.victor.examples.extensions.FrankAgentReadyMatcher.ready;
import static org.hamcrest.core.Is.is;

public class VictorTest extends PollableExpressions {
    public static final String VIGOR_PROPERTIES_FILE = "vigor.properties";
    public static IosApplication application;
    public static IosDevice device;
    public static PollTimer timer;

    @BeforeClass
    public static void startApplicationInDevice() {
        Properties properties = loadProperties();
        IosDeviceConfiguration configuration = new IosDeviceConfiguration((Map)properties);
        FrankAgent frank = CreateFrankAgent.fromProperties(properties);
        application = new FrankIosApplication(frank);
        timer = createTimer(properties);
        device = CreateIosDevice.withCapabilities(configuration);
        device.start();
        waitUntil(frank, timer, is(ready()));
    }

    @AfterClass
    public static void stopDevice() {
        if(device != null) device.stop();
    }

    @Override
    public PollTimer eventually() { return timer; }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new ApplicationOrientationQuery();
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(VIGOR_PROPERTIES_FILE));
        } catch (IOException cause) {
            throw new RuntimeException("Unable to load properties from file " + VIGOR_PROPERTIES_FILE, cause);
        }
        return properties;
    }

    private static PollTimer createTimer(Properties properties) {
        Integer timeout = Integer.parseInt(properties.getProperty("polling.timeout"));
        Integer pollingInterval = Integer.parseInt(properties.getProperty("polling.interval"));
        return new SystemClockPollTimer(timeout, pollingInterval);
    }
}
