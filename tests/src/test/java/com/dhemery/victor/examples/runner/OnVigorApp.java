package com.dhemery.victor.examples.runner;

import com.dhemery.configuring.Configuration;
import com.dhemery.configuring.LoadProperties;
import com.dhemery.core.Builder;
import com.dhemery.core.Lazily;
import com.dhemery.core.Lazy;
import com.dhemery.polling.*;
import com.dhemery.publishing.Channel;
import com.dhemery.publishing.Distributor;
import com.dhemery.publishing.MethodSubscriptionChannel;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosDevice;
import com.dhemery.victor.Victor;
import org.junit.After;
import org.junit.Before;

import static com.dhemery.victor.examples.application.ApplicationQueries.isRunning;

public class OnVigorApp extends Expressive {
    private static final String[] VIGOR_PROPERTIES_FILES = {"default.properties", "my.properties"};
    private static final Lazy<Channel> CHANNEL = Lazily.build(theChannel());
    private static final Lazy<Configuration> CONFIGURATION = Lazily.build(theConfiguration());
    private final Lazy<IosApplication> application = Lazily.build(theApplication());
    private final Lazy<IosDevice> device = Lazily.build(theDevice());
    private final Lazy<Victor> victor = Lazily.build(theVictor());

    protected OnVigorApp() {
        super(thePoller(), theTicker());
    }

    @Before
    public void startDevice() {
        device().start();
        waitUntil(application(), isRunning());
    }

    @After
    public void stopDevice() {
        if(!device.hasAValue()) return;
        device().stop();
    }

    protected IosApplication application() {
        return application.get();
    }

    protected static Configuration configuration() {
        return CONFIGURATION.get();
    }

    protected IosDevice device() {
        return device.get();
    }

    protected static Distributor events() {
        return CHANNEL.get();
    }

    protected Victor victor() {
        return victor.get();
    }

    private Builder<? extends IosApplication> theApplication() {
        return new Builder<IosApplication>() {
            @Override
            public IosApplication build() {
                return victor().application();
            }
        };
    }

    private static Builder<? extends Channel> theChannel() {
        return new Builder<Channel>() {
            @Override
            public Channel build() {
                Channel channel = new MethodSubscriptionChannel();
                channel.subscribe(new VigorCommandLogger());
                channel.subscribe(new VigorFrankLogger());
                channel.subscribe(new VigorPollLogger());
                return channel;
            }
        };
    }

    private static Builder<? extends Configuration> theConfiguration() {
        return new Builder<Configuration>(){
            @Override
            public Configuration build() {
                Configuration configuration = new Configuration();
                LoadProperties.fromFiles(VIGOR_PROPERTIES_FILES).into(configuration);
                return configuration;
            }
        };
    }

    private Builder<? extends IosDevice> theDevice() {
        return new Builder<IosDevice>() {
            @Override
            public IosDevice build() {
                return victor().device();
            }
        };
    }

    private static Poller thePoller() {
        PollEvaluator simpleEvaluator = new SimplePollEvaluator();
        PollEvaluator publishingEvaluator = new PublishingPollEvaluator(CHANNEL.get(), simpleEvaluator);
        return new EvaluatingPoller(publishingEvaluator);
    }

    private static Ticker theTicker() {
        long timeout = Long.parseLong(configuration().requiredOption("polling.timeout"));
        long interval = Long.parseLong(configuration().requiredOption("polling.interval"));
        return new SystemClockTicker(timeout, interval);
    }

    private Builder<? extends Victor> theVictor() {
        return new Builder<Victor>() {
            @Override
            public Victor build() {
                return new Victor(configuration(), CHANNEL.get());
            }
        };
    }
}
