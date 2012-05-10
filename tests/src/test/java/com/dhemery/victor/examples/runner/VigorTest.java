package com.dhemery.victor.examples.runner;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.properties.ReadProperties;
import com.dhemery.victor.Configuration;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.device.CreateIosDevice;
import com.dhemery.victor.examples.extensions.ApplicationOrientationQuery;
import com.dhemery.victor.frank.CreateIosApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Map;

import static com.dhemery.victor.examples.extensions.ApplicationRunningMatcher.running;
import static org.hamcrest.Matchers.is;

public class VigorTest extends PollableExpressions {
    public static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    public static IosApplication application;
    public static IosDevice device;
    public static PollTimer timer;

    @BeforeClass
    public static void startApplicationInDevice() {
        Configuration configuration = readConfiguration();
        device = CreateIosDevice.withConfiguration(configuration);
        application = CreateIosApplication.withConfiguration(configuration);
        timer = createTimer(configuration);
        device.start();
        waitUntil(application, timer, is(running()));
    }

    private static Configuration readConfiguration() {
        return new Configuration(ReadProperties.fromFiles(VIGOR_PROPERTIES_FILES).asMap());
    }

    @AfterClass
    public static void stopDevice() {
        if (device != null) device.stop();
    }

    @Override
    public PollTimer eventually() {
        return timer;
    }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new ApplicationOrientationQuery();
    }

    private static PollTimer createTimer(Configuration configuration) {
        Integer timeout = Integer.parseInt(configuration.option("polling.timeout"));
        Integer pollingInterval = Integer.parseInt(configuration.option("polling.interval"));
        return new SystemClockPollTimer(timeout, pollingInterval);
    }
}
