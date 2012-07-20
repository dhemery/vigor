package com.dhemery.victor.examples.runner;

import com.dhemery.os.OSCommand;
import com.dhemery.os.OSCommandEvent;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorCommandLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void willRun(OSCommandEvent.WillRun event) {
        OSCommand command = event.command;
        log.info("{} --> {} {}", new Object[]{ command.description, command.path, command.arguments});
    }

    @Subscribe
    public void returned(OSCommandEvent.Returned event) {
        OSCommand command = event.command;
        log.info("{} <-- {}", command.description, event.output);
    }
}
