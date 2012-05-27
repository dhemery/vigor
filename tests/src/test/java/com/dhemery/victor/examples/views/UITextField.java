package com.dhemery.victor.examples.views;

import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;

public class UITextField extends UIView {
    public UITextField(IosApplication application, By query) {
        super(application, query);
    }

    public void appendText(String text) {
        sendMessage("DFX_appendText:", text);
    }

    public void done() {
        sendMessage("DFX_return");
    }

    public void insertText(String text, int location) {
        sendMessage("DFX_insertText:atLocation:", text, location);
    }

    public void setText(String text) {
        sendMessage("DFX_setText:", text);
    }
}
