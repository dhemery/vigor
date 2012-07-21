package com.dhemery.victor.examples.runner;

import com.dhemery.victor.frank.events.*;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VigorFrankLogger {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void accessibilityCheckRequest(WillRequestAccessibilityCheck ignored) {
        log.info("Frank accessibility check -->");
    }

    @Subscribe
    public void accessibilityCheckResponse(AccessibilityCheckReturned accessibility) {
        log.info("Frank accessibility check <-- {}", accessibility.enabled());
    }

    @Subscribe
    public void appExecRequest(WillRequestAppExec request) {
        log.info("Frank app exec --> {} {}", request.name(), request.arguments());
    }

    @Subscribe
    public void appExecResponse(AppExecReturned appExec) {
        log.info("Frank app exec <-- {}", appExec.returnValue());
    }

    @Subscribe
    public void dumpRequest(WillRequestDump ignored) {
        log.info("Frank dump -->");
    }

    @Subscribe
    public void dumpResponse(DumpReturned ignored) {
        log.info("Frank dump <--");
    }

    @Subscribe
    public void mapRequest(WillRequestMap map) {
        log.info("Frank map --> {} {} {} {}", new Object[]{map.engine(), map.query(), map.name(), map.arguments()});
    }

    @Subscribe
    public void mapResponse(MapReturned map) {
        log.info("Frank map <-- {}", map.returnValues());
    }

    @Subscribe
    public void orientationRequest(WillRequestOrientation ignored) {
        log.info("Frank orientation -->");
    }

    @Subscribe
    public void orientationResponse(OrientationReturned application) {
        log.info("Frank orientation <-- {}", application.orientation());
    }

    @Subscribe
    public void typeIntoKeyboardRequest(WillRequestTypeIntoKeyboard typeIntoKeyboard) {
        log.info("--> type into keyboard {}", typeIntoKeyboard.text());
    }

    @Subscribe
    public void typeIntoKeyboardResponse(TypeIntoKeyboardReturned ignored) {
        log.info("<-- type into keyboard");
    }
}
