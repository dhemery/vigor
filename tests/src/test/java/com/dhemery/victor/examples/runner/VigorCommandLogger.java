package com.dhemery.victor.examples.runner;

import com.dhemery.os.OSCommand;
import com.dhemery.os.OSCommandSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorCommandLogger implements OSCommandSubscriber {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void willRun(OSCommand command) {
        log.info("{} --> {} {}", new Object[]{ command.description(), command.path(), command.arguments()});
    }

    @Override
    public void ran(OSCommand command) {
    }

    @Override
    public void returned(OSCommand command, String output) {
        log.info("{} <-- {}", command.description(), output);
    }
}
