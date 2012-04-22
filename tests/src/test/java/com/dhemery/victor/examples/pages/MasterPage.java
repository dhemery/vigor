package com.dhemery.victor.examples.pages;

import static com.dhemery.victor.view.ViewExtensions.touch;
import static com.dhemery.victor.view.ViewExtensions.visible;
import static org.hamcrest.core.Is.is;

import com.dhemery.polling.PollTimer;
import com.dhemery.polling.PollableExpressions;
import com.dhemery.polling.SystemClockPollTimer;
import com.dhemery.victor.ApplicationDriver;
import com.dhemery.victor.IgorSelector;
import com.dhemery.victor.ViewDriver;
import com.dhemery.victor.ViewSelector;

import java.util.List;

public class MasterPage extends PollableExpressions {
    private static final ViewSelector ADD_BUTTON = new IgorSelector("UINavigationButton[accessibilityLabel=='Add']");
    private static final ViewSelector CONFIRM_DELETION_BUTTON = new IgorSelector("UITableViewCellDeleteConfirmationControl[accessibilityLabel == 'Confirm Deletion']");
    private static final String DELETE_BUTTON_TEMPLATE = "UITableView[accessibilityLabel=='Empty list'] > UITableViewCell > UITableViewCellEditControl[accessibilityLabel=='%s']";
    private static final ViewSelector DELETE_BUTTONS = new IgorSelector("UITableView[accessibilityLabel=='Empty list'] > UITableViewCell > UITableViewCellEditControl[accessibilityLabel BEGINSWITH 'Delete']");
    private static final ViewSelector DONE_BUTTON = new IgorSelector("UINavigationButton[accessibilityLabel=='Done']");
    private static final ViewSelector EDIT_BUTTON = new IgorSelector("UINavigationButton[accessibilityLabel=='Edit']");
    private static final String ITEM_TEMPLATE = "UITableView[accessibilityLabel=='Empty list'] > UITableViewCell[accessibilityLabel=='%s']";
    private static final ViewSelector ITEMS = new IgorSelector("UITableView[accessibilityLabel=='Empty list'] > UITableViewCell");
    private final ApplicationDriver application;

    public MasterPage(ApplicationDriver application) {
        this.application = application;
    }

    private ViewDriver addButton() {
        return application.view(ADD_BUTTON);
    }

    public void addItem() {
        addButton().call(touch());
    }

    private ViewDriver confirmDeletionButton() {
        return application.view(CONFIRM_DELETION_BUTTON);
    }

    private ViewDriver deleteButtonAtRow(Integer i) {
        List<String> labels = deleteButtons().call("accessibilityLabel");
        return deleteButtonNamed(labels.get(i));
    }

    private ViewDriver deleteButtonNamed(String name) {
        ViewSelector selector = new IgorSelector(String.format(DELETE_BUTTON_TEMPLATE, name));
        return application.view(selector);
    }

    private ViewDriver deleteButtons() {
        return application.view(DELETE_BUTTONS);
    }

    public void deleteItemAtRow(Integer i) {
        editButton().call(touch());
        when(deleteButtonAtRow(i), is(visible()), touch());
        when(confirmDeletionButton(), is(visible()), touch());
        doneButton().call(touch());
    }

    private ViewDriver doneButton() {
        return application.view(DONE_BUTTON);
    }

    private ViewDriver editButton() {
        return application.view(EDIT_BUTTON);
    }

    @Override
    public PollTimer eventually() {
        return new SystemClockPollTimer(10000);
    }

    private ViewDriver itemAtRow(Integer i) {
        List<String> itemLabels = items().call("accessibilityLabel");
        return itemNamed(itemLabels.get(i));
    }

    private ViewDriver itemNamed(String name) {
        ViewSelector selector = new IgorSelector(String.format(ITEM_TEMPLATE, name));
        return application.view(selector);
    }

    private ViewDriver items() {
        return application.view(ITEMS);
    }

    public Integer numberOfItems() {
        return items().call("accessibilityLabel").size();
    }

    public void visitItemAtRow(Integer i) {
        itemAtRow(i).call(touch());
    }
}
