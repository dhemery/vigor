package com.dhemery.victor.examples.runner;

import com.dhemery.os.Command;
import com.dhemery.os.events.Returned;
import com.dhemery.os.events.WillRun;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorCommandLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void willRun(WillRun event) {
        Command command = event.command();
        log.info("{} --> {} {}", new Object[]{ command.description(), command.path(), command.arguments()});
    }

    @Subscribe
    public void returned(Returned event) {
        Command command = event.command();
        log.info("{} <-- {}", command.description(), event.output());
    }
}
