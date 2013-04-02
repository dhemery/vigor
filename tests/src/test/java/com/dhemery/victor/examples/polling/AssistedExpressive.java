package com.dhemery.victor.examples.polling;

import com.dhemery.expressing.Expressive;
import com.dhemery.polling.Ticker;
import com.dhemery.victor.examples.polling.PollingAssistant;

public class AssistedExpressive extends Expressive {
    private final PollingAssistant assistant;

    public AssistedExpressive(PollingAssistant assistant) {
        this.assistant = assistant;
    }

    @Override
    public Ticker createDefaultTicker() {
        return assistant.createDefaultTicker();
    }
}
