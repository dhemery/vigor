package com.dhemery.victor.examples.runner;

import com.dhemery.polling.events.ConditionSatisfied;
import com.dhemery.polling.events.ConditionUnsatisfied;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class VigorPollLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe public <S> void satisfied(ConditionSatisfied event) {
        log.info(format("Satisfied %s", event.condition()));
    }

    @Subscribe public <S> void unsatisfied(ConditionUnsatisfied event) {
        log.info(format("Waiting until %s", event.condition()));
    }
}
