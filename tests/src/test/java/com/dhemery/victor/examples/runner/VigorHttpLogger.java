package com.dhemery.victor.examples.runner;

import com.dhemery.network.events.GetResponded;
import com.dhemery.network.events.PutResponded;
import com.dhemery.network.events.WillGet;
import com.dhemery.network.events.WillPut;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorHttpLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void logGetRequest(WillGet get) {
        log.info("Http GET --> {}", get.endpoint());
    }

    @Subscribe
    public void logGetResponse(GetResponded get) {
        log.info("Http GET <-- {} {}", get.endpoint(), get.response());
    }

    @Subscribe
    public void logPutRequest(WillPut put) {
        log.info("Http PUT --> {} {}", put.endpoint(), put.message());
    }

    @Subscribe
    public void logPutResponse(PutResponded put) {
        log.info("Http PUT <-- {} {}", put.endpoint(), put.response());
    }
}
