package com.dhemery.victor.examples.runner;

import com.dhemery.network.events.*;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorHttpLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void logGetRequest(WillSendGet get) {
        log.info("Http GET --> {}", get.path());
    }

    @Subscribe
    public void logGetResponse(GetResponded get) {
        log.info("Http GET <-- {} {}", get.path(), get.response());
    }

    @Subscribe
    public void logGetThrew(GetThrew get) {
        log.info("Http GET !!! {} {}", get.path(), get.exception());
    }

    @Subscribe
    public void logPutRequest(WillSendPut put) {
        log.info("Http PUT --> {} {}", put.path(), put.message());
    }

    @Subscribe
    public void logPutResponse(PutResponded put) {
        log.info("Http PUT <-- {} {}", put.path(), put.response());
    }

    @Subscribe
    public void logPutThrew(PutThrew put) {
        log.info("Http PUT !!! {} {}", put.path(), put.exception());
    }
}
