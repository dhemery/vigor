package com.dhemery.victor.examples.runner;

import com.dhemery.network.events.GetResponded;
import com.dhemery.network.events.PutResponded;
import com.dhemery.network.events.WillSendGet;
import com.dhemery.network.events.WillSendPut;
import com.dhemery.publishing.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorHttpLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void logGetRequest(WillSendGet get) {
        log.info("Http GET --> {}", get.resource());
    }

    @Subscribe
    public void logGetResponse(GetResponded get) {
        log.info("Http GET <-- {} {}", get.resource(), get.response());
    }

    @Subscribe
    public void logPutRequest(WillSendPut put) {
        log.info("Http PUT --> {} {}", put.resource(), put.message());
    }

    @Subscribe
    public void logPutResponse(PutResponded put) {
        log.info("Http PUT <-- {} {}", put.resource(), put.response());
    }
}
