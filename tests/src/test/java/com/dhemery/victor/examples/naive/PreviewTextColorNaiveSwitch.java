package com.dhemery.victor.examples.naive;

import com.dhemery.victor.examples.tests.PreviewTextColor;

public class PreviewTextColorNaiveSwitch extends PreviewTextColor {
    private static final String BLUE = "{\"green\":1,\"red\":0,\"alpha\":1,\"blue\":0}";
    private static final String GREEN = "{\"green\":0,\"red\":0,\"alpha\":1,\"blue\":1}";
    private static final String RED = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":0}";
    private static final String YELLOW = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":1}";

    @Override
    protected void enablePrefix() {
        setPrefixEnabled(true);
    }

    @Override
    protected void disablePrefix() {
        setPrefixEnabled(false);
    }

    private void setPrefixEnabled(boolean setting) {
        prefixEnabled.sendMessage("setOn:animated:", setting, true);
        waitForAnimation();
    }
}
