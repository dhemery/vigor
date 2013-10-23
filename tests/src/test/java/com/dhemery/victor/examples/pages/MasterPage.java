package com.dhemery.victor.examples.pages;

import com.dhemery.expressing.PollingAssistant;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosViewIdentifier;
import com.dhemery.victor.examples.views.UITableViewCell;
import com.dhemery.victor.examples.views.UIView;

import java.util.List;

import static com.dhemery.victor.Igor.igor;
import static com.dhemery.victor.examples.views.UIViewQueries.*;
import static org.hamcrest.Matchers.greaterThan;

public class MasterPage extends Page {
    private static final IosViewIdentifier ADD_BUTTON = igor("UINavigationButton[accessibilityLabel=='Add']");
    private static final IosViewIdentifier DONE_BUTTON = igor("UINavigationButton[accessibilityLabel=='Done']");
    private static final IosViewIdentifier EDIT_BUTTON = igor("UINavigationButton[accessibilityLabel=='Edit']");

    private static final IosViewIdentifier CELL = igor("UITableViewCell*");
    private static final IosViewIdentifier CELL_LABEL = igor(CELL.pattern() + " UILabel");
    private static final String CELL_WITH_LABEL = "(" + CELL_LABEL.pattern() + "[accessibilityLabel=='%s'])";

    public MasterPage(IosApplication application, PollingAssistant assistant) {
        super(application, assistant);
    }

    public void addItem() {
        when(view(ADD_BUTTON), is(visible())).tap();
    }

    public UITableViewCell items() {
        return cell(CELL);
    }

    public void deleteItem(Integer i) {
        editButton().tap();
        UITableViewCell item = item(i);
        waitUntil(item, is(not(animating())));
        regret(500);
        when(item.deleteButton(), is(visible())).tap();
        when(item.confirmationButton(), is(visible())).tap();
        waitUntil(item, is(visible()));
        doneButton().tap();
    }

    private void regret(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
        }
    }


    public UIView doneButton() {
        return view(DONE_BUTTON);
    }

    public UIView editButton() {
        return view(EDIT_BUTTON);
    }

    public UITableViewCell item(Integer i) {
        return itemWithLabel(itemLabel(i));
    }

    private String itemLabel(Integer i) {
        waitUntil(items(), count(), is(greaterThan(i)));
        return itemLabels().get(i);
    }

    private List<String> itemLabels() {
        return view(CELL_LABEL).sendMessage("accessibilityLabel");
    }

    private UITableViewCell itemWithLabel(String label) {
        return cell(igor(String.format(CELL_WITH_LABEL, label)));
    }

    private UITableViewCell cell(IosViewIdentifier id) {
        return new UITableViewCell(application(), id);
    }

    public void visitItem(Integer i) {
        item(i).tap();
    }
}
