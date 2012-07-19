package com.dhemery.victor.examples.runner;

import com.dhemery.victor.frank.FrankSubscriber;
import com.dhemery.victor.frankly.AccessibilityCheckResponse;
import com.dhemery.victor.frankly.MessageResponse;
import com.dhemery.victor.frankly.OrientationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorFrankLogger implements FrankSubscriber {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void accessibilityCheckRequest() {
        log.info("Frank accessibility check -->");
    }

    @Override
    public void accessibilityCheckResponse(AccessibilityCheckResponse response) {
        log.info("Frank accessibility check <-- {}", response.accessibilityEnabled());
    }

    @Override
    public void appExecRequest(String name, Object[] arguments) {
        log.info("Frank app exec --> {} {}", name, arguments);
    }

    @Override
    public void appExecResponse(String name, Object[] arguments, MessageResponse response) {
        log.info("Frank app exec <-- {}", response.results());
    }

    @Override
    public void dumpRequest() {
        log.info("Frank dump -->");
    }

    @Override
    public void dumpResponse(String response) {
        log.info("Frank dump <--");
    }

    @Override
    public void mapRequest(String engine, String query, String name, Object[] arguments) {
        log.info("Frank map --> {} {} {} {}", new Object[]{engine, query, name, arguments});
    }

    @Override
    public void mapResponse(String engine, String query, String name, Object[] arguments, MessageResponse response) {
        log.info("Frank map <-- {}", response.results());
    }

    @Override
    public void orientationRequest() {
        log.info("Frank orientation -->");
    }

    @Override
    public void orientationResponse(OrientationResponse response) {
        log.info("Frank orientation <-- {}", response.orientation());
    }

    @Override
    public void typeIntoKeyboardRequest(String text) {
        log.info("--> type into keyboard {}", text);
    }

    @Override
    public void typeIntoKeyboardResponse() {
        log.info("<-- type into keyboard");
    }
}
