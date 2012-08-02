package com.dhemery.victor.examples.runner;

import com.dhemery.polling.events.ConditionSatisfied;
import com.dhemery.polling.events.ConditionUnsatisfied;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class VigorPollLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe public <S> void satisfied(ConditionSatisfied poll) {
        log.info(format("Satisfied %s", poll.condition()));
    }

    @Subscribe public <S> void unsatisfied(ConditionUnsatisfied poll) {
        log.info(format("Waiting (%s) until %s", poll.failureCount(), poll.condition()));
    }
}
