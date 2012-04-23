package com.dhemery.victor.examples.extensions;

import com.dhemery.polling.Action;
import com.dhemery.victor.IosView;

public class TouchViewAction implements Action<IosView> {
    @Override
    public void executeOn(IosView view) {
        view.sendMessage("touch");
    }
}
