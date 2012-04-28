package com.dhemery.victor.examples.extensions;

import com.dhemery.polling.Action;
import com.dhemery.victor.IosView;

public class TableCellConfirmDeletionAction implements Action<IosView> {
    @Override
    public void executeOn(IosView cell) {
        cell.sendMessage("FEX_confirmDeletion");
    }

    public static Action<IosView> confirmDeletion() {
        return new TableCellConfirmDeletionAction();
    }

    public static void confirmDeletionOf(IosView cell) {
        confirmDeletion().executeOn(cell);
    }
}
