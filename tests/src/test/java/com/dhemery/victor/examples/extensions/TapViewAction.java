package com.dhemery.victor.examples.extensions;

import com.dhemery.polling.Action;
import com.dhemery.victor.IosView;

public class TapViewAction implements Action<IosView> {
    @Override
    public void executeOn(IosView view) {
        view.sendMessage("tap");
    }

    public static Action<? super IosView> tap() {
        return new TapViewAction();
    }
}
