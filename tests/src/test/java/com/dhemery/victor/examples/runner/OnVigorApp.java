package com.dhemery.victor.examples.runner;

import com.dhemery.configuration.Configuration;
import com.dhemery.configuration.LoadProperties;
import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.Query;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.victor.*;
import com.dhemery.victor.examples.application.ApplicationOrientationQuery;
import com.dhemery.victor.examples.views.UILabel;
import com.dhemery.victor.examples.views.UISwitch;
import com.dhemery.victor.examples.views.UITextField;
import com.dhemery.victor.examples.views.UIView;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.dhemery.victor.examples.application.ApplicationRunningMatcher.running;
import static org.hamcrest.Matchers.is;

public class OnVigorApp extends PollableExpressions {
    private static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    protected static final Configuration configuration = new Configuration();
    protected IosApplication application;
    protected IosDevice device;
    protected IosViewFactory viewFactory;
    protected PollTimer timer;
    private int demoScale;
    private Victor victor;

    @BeforeClass
    public static void startApplicationInDevice() {
        LoadProperties.fromFiles(VIGOR_PROPERTIES_FILES).into(configuration);
    }

    @Before
    public void waitForApplication() {
        victor = new Victor(configuration);
        victor.events().subscribe(new VigorCommandLogger());
        victor.events().subscribe(new VigorFrankLogger());
//        victor.events().subscribe(new VigorHttpLogger());
        demoScale = Integer.parseInt(configuration.requiredOption("demo.scale"));
        viewFactory = victor.viewFactory();
        device = victor.device();
        application = victor.application();
        timer = createTimer(configuration);
        pollPublisher().subscribe(new VigorPollLogger());
        device.start();
        waitUntil(application, timer, is(running()));
    }

    @After
    public void stopDevice() {
        if (device != null) device.stop();
    }

    @Override
    public PollTimer eventually() {
        return timer;
    }

    protected Query<IosApplication, IosApplicationOrientation> orientation() {
        return new ApplicationOrientationQuery();
    }

    private static PollTimer createTimer(Configuration configuration) {
        Integer timeout = Integer.parseInt(configuration.option("polling.timeout"));
        Integer pollingInterval = Integer.parseInt(configuration.option("polling.interval"));
        return new SystemClockPollTimer(timeout, pollingInterval);
    }

    protected void demo(int i) {
        if(demoScale == 0) return;
        try {
            Thread.sleep(i * demoScale);
        } catch (InterruptedException e) {
        }
    }

    protected UISwitch toggle(By query) {
        return new UISwitch(viewFactory, query);
    }

    protected UITextField textField(By query) {
        return new UITextField(viewFactory, query);
    }

    protected UILabel label(By query) {
        return new UILabel(viewFactory, query);
    }

    protected UIView button(By query) {
        return new UIView(viewFactory, query);
    }

    protected void waitForAnimation() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    protected void rotateRight() {
        device.rotateRight();
    }

    protected void rotateLeft() {
        device.rotateLeft();
    }
}
