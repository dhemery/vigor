package com.dhemery.victor.examples.runner;

import com.dhemery.polling.events.ConditionDissatisfied;
import com.dhemery.polling.events.ConditionSatisfied;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class VigorPollLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void satisfied(ConditionSatisfied condition) {
        log.info(format("Satisfied %s", condition.description()));
    }

    @Subscribe
    public void unsatisfied(ConditionDissatisfied poll) {
        log.info(format("Waiting (%s) until %s", poll.failureCount(), poll.description()));
    }
}
