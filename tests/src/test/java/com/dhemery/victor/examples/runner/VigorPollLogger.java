package com.dhemery.victor.examples.runner;

import com.dhemery.polling.events.Satisfied;
import com.dhemery.polling.events.Unsatisfied;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorPollLogger {
    Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe public <S> void satisfied(Satisfied event) {
        log.info("Satisfied {}", event.condition());
    }

    @Subscribe public <S> void unsatisfied(Unsatisfied event) {
        log.info("Waiting until {}", event.condition());
    }
}
