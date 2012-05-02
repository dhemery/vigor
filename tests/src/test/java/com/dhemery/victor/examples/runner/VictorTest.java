package com.dhemery.victor.examples.runner;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.properties.ReadProperties;
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

import java.util.Map;

import static com.dhemery.victor.examples.extensions.FrankRespondsMatcher.respondsToRequests;
import static org.hamcrest.core.Is.is;

public class VictorTest extends PollableExpressions {
    public static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    public static IosApplication application;
    public static IosDevice device;
    public static PollTimer timer;

    @BeforeClass
    public static void startApplicationInDevice() {
        Map<String, String> properties = ReadProperties.fromFiles(VIGOR_PROPERTIES_FILES).asMap();
        FrankAgent frank = CreateFrankAgent.withConfiguration(properties);
        application = new FrankIosApplication(frank);
        timer = createTimer(properties);
        IosDeviceConfiguration deviceConfiguration = new IosDeviceConfiguration(properties);
        device = CreateIosDevice.withConfiguration(deviceConfiguration);
//        device.start();
        waitUntil(frank, timer, respondsToRequests());
    }

    @AfterClass
    public static void stopDevice() {
//        if (device != null) device.stop();
    }

    @Override
    public PollTimer eventually() {
        return timer;
    }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new ApplicationOrientationQuery();
    }

    private static PollTimer createTimer(Map<String, String> properties) {
        Integer timeout = Integer.parseInt(properties.get("polling.timeout"));
        Integer pollingInterval = Integer.parseInt(properties.get("polling.interval"));
        return new SystemClockPollTimer(timeout, pollingInterval);
    }
}
