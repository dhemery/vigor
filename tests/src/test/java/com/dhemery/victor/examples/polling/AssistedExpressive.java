package com.dhemery.victor.examples.polling;

import com.dhemery.core.Condition;
import com.dhemery.expressing.Expressive;
import com.dhemery.polling.Ticker;
import com.dhemery.victor.examples.polling.PollingAssistant;

public class AssistedExpressive extends Expressive {
    private final PollingAssistant assistant;

    public AssistedExpressive(PollingAssistant assistant) {
        this.assistant = assistant;
    }

    public PollingAssistant pollingAssistant() {
        return assistant;
    }

    @Override
    public Condition prepareToPoll(Condition condition) {
        return assistant.prepareToPoll(condition);
    }

    @Override
    public Ticker createDefaultTicker() {
        return assistant.createDefaultTicker();
    }
}
