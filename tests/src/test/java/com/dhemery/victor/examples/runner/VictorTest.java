package com.dhemery.victor.examples.runner;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.properties.RequiredProperties;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.device.LocalSimulator;
import com.dhemery.victor.device.SimulatedIosDevice;
import com.dhemery.victor.examples.tests.OrientationQuery;
import com.dhemery.victor.frank.FrankAgent;
import com.dhemery.victor.frank.FrankIosApplication;
import com.dhemery.victor.frank.IosViewAgent;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.dhemery.victor.examples.extensions.AgentReadyMatcher.ready;
import static org.hamcrest.core.Is.is;

public class VictorTest extends PollableExpressions {
	private static IosApplication application;
    private static FrankAgent frank;
	private static IosDevice device;
	private static PollTimer timer;
    private static RequiredProperties configuration;
    private static LocalSimulator simulator;

    @BeforeClass
	public static void startApplication() {
        configuration = new RequiredProperties("default.properties", "./my.properties");
        simulator = new LocalSimulator(sdkRoot(), simulatorBinaryPath());
        simulator.startWithApplication(applicationBinaryPath());
        device = new SimulatedIosDevice(simulator);
        frank = CreateFrankAgent.fromProperties(configuration.properties());
        timer = timer(configuration);
        waitUntil(frank, timer, is(ready()));
		application = new FrankIosApplication(frank);
	}

    @AfterClass
	public static void stopApplication() {
        simulator.stop();
	}

    public IosViewAgent viewAgent() { return frank; }
	public IosApplication application() { return application; }
	public IosDevice device() { return device; }

	@Override
    public PollTimer eventually() {
        return timer;
    }

    protected Query<IosApplication, IosApplication.Orientation> orientation() {
        return new OrientationQuery();
    }

    private static PollTimer timer(RequiredProperties configuration) {
        Integer timeout = configuration.getInteger("polling.timeout");
        Integer pollingInterval = configuration.getInteger("polling.interval");
        return new SystemClockPollTimer(timeout, pollingInterval);
    }





    private static final String SDK_ROOT_TEMPLATE = "%s/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator%s.sdk";
    private static final String SIMULATOR_BINARY_PATH_TEMPLATE = "%s/Platforms/iPhoneSimulator.platform/Developer/Applications/iPhone Simulator.app/Contents/MacOS/iPhone Simulator";

    private static String applicationBinaryPath() {
        return configuration.get("application.binary.path");
    }

    // todo use xcode-switch -print-path
    // todo allow property to specify full path
    private static String developerRoot() {
        return "/Applications/Xcode.app/Contents/Developer";
    }

    // todo allow property to specify full path
    private static String sdkRoot() {
        return String.format(SDK_ROOT_TEMPLATE, developerRoot(), sdkVersion());
    }

    // todo allow defaulting to highest numbered version
    private static String sdkVersion() {
        return configuration.get("sdk.version");
    }

    // todo allow property to specify full path
    private static String simulatorBinaryPath() {
        return String.format(SIMULATOR_BINARY_PATH_TEMPLATE, developerRoot());
    }
}
