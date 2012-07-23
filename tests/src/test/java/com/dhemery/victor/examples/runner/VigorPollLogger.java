package com.dhemery.victor.examples.runner;

import com.dhemery.polling.Satisfied;
import com.dhemery.polling.Unsatisfied;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorPollLogger {
    Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe public <S> void satisfied(Satisfied<S> event) {
        log.info("Satisfied {} {}", event.subject(), event.criteria());
    }

    @Subscribe public <S> void unsatisfied(Unsatisfied<S> event) {
        log.info("Waiting ({}) until {} {}", new Object[] { event.count(), event.subject(), event.criteria() });
    }
}
