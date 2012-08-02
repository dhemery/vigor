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
    private final Lazy<Configuration> configuration = Lazily.build(theConfiguration());
    private final Lazy<IosApplication> application = Lazily.build(theApplication());
    private final Lazy<IosDevice> device = Lazily.build(theDevice());
    private final Lazy<Channel> channel = Lazily.build(theChannel());
    private final Lazy<Poller> poller = Lazily.build(thePoller());
    private final Lazy<Victor> victor = Lazily.build(theVictor());

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

    protected Configuration configuration() {
        return configuration.get();
    }

    protected IosDevice device() {
        return device.get();
    }

    protected Distributor events() {
        return channel.get();
    }

    @Override
    protected Poller defaultPoller() {
        return poller.get();
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

    protected Builder<? extends Poller> thePoller() {
        return new Builder<Poller>() {
            @Override
            public Poller build() {
                long timeout = Long.parseLong(configuration().requiredOption("polling.timeout"));
                long interval = Long.parseLong(configuration().requiredOption("polling.interval"));
                PollTimer timer = new SystemClockPollTimer(timeout, interval);
                PollEvaluator evaluator = new PublishingPollEvaluator(channel.get());
                return new DelegatingPoller(timer, evaluator);
            }
        };
    }

    private Builder<? extends Victor> theVictor() {
        return new Builder<Victor>() {
            @Override
            public Victor build() {
                return new Victor(configuration(), channel.get());
            }
        };
    }
}
