package com.dhemery.victor.examples.polling;

import com.dhemery.core.Condition;
import com.dhemery.expressing.PublishingCondition;
import com.dhemery.polling.SystemClockTicker;
import com.dhemery.polling.Ticker;
import com.dhemery.publishing.Publisher;

public class PollingAssistant {
    private final Publisher publisher;
    private final long pollingTimeout;
    private final long pollingInterval;

    public PollingAssistant(Publisher publisher, long pollingTimeout, long pollingInterval) {
        this.publisher = publisher;
        this.pollingTimeout = pollingTimeout;
        this.pollingInterval = pollingInterval;
    }

    public Ticker createDefaultTicker() {
        return new SystemClockTicker(pollingTimeout, pollingInterval);
    }

    public Condition prepareToPoll(Condition condition) {
        return new PublishingCondition(condition, publisher);
    }
}
