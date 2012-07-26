package com.dhemery.victor.examples.naive;

import com.dhemery.victor.examples.tests.PreviewBackgroundColor;

public class PreviewBackgroundColorNaiveTextField extends PreviewBackgroundColor {
    private static final String BLUE = "{\"green\":1,\"red\":0,\"alpha\":1,\"blue\":0}";
    private static final String GREEN = "{\"green\":0,\"red\":0,\"alpha\":1,\"blue\":1}";
    private static final String RED = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":0}";
    private static final String YELLOW = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":1}";

    @Override
    protected void beginEditing() {
        prefixEnabled.sendMessage("becomeFirstResponder");
    }

    @Override
    protected void endEditing() {
        prefixEnabled.sendMessage("resignFirstResponder");
    }
}
