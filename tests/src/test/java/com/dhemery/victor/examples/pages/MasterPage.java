package com.dhemery.victor.examples.pages;

import com.dhemery.polling.PollTimer;
import com.dhemery.victor.By;
import com.dhemery.victor.IosApplication;
import com.dhemery.victor.IosView;
import org.hamcrest.CoreMatchers;

import java.util.List;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.extensions.ViewIsAnimatingMatcher.animating;
import static com.dhemery.victor.examples.extensions.ViewListIsEmptyMatcher.empty;
import static com.dhemery.victor.examples.extensions.TapViewAction.tap;
import static com.dhemery.victor.examples.extensions.ViewIsTappableMatcher.tappable;
import static com.dhemery.victor.examples.extensions.ViewIsVisibleMatcher.visible;
import static com.dhemery.victor.examples.extensions.ViewListSizeQuery.size;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class MasterPage extends Page {
    private static final By ADD_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Add']");
    private static final By CONFIRM_DELETION_BUTTON = By.igor("UITableViewCellDeleteConfirmationControl[accessibilityLabel == 'Confirm Deletion']");
    private static final String DELETE_BUTTON_WITH_ACCESSIBILITY_LABEL = "UITableView[accessibilityLabel=='Empty list'] > UITableViewCell > UITableViewCellEditControl[accessibilityLabel=='%s']";
    private static final By DELETE_BUTTONS = By.igor("UITableView[accessibilityLabel=='Empty list'] > UITableViewCell > UITableViewCellEditControl[accessibilityLabel BEGINSWITH 'Delete']");
    private static final By DONE_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Done']");
    private static final By EDIT_BUTTON = By.igor("UINavigationButton[accessibilityLabel=='Edit']");
    private static final By ITEMS = By.igor("UITableView[accessibilityLabel=='Empty list'] UITableViewCell UILabel");
    private static final String ITEM_WITH_ACCESSIBILITY_LABEL = ITEMS.selector + "[accessibilityLabel=='%s']";

    public MasterPage(IosApplication application, PollTimer timer) {
        super(application, timer);
    }

    private IosView addButton() {
        return view(ADD_BUTTON);
    }

    public void addItem() {
        addButton().sendMessage("tap");
    }

    private IosView confirmDeletionButton() {
        return view(CONFIRM_DELETION_BUTTON);
    }

    private IosView deleteButtonAtRow(Integer i) {
        waitUntil(deleteButtons(), has(size(), greaterThan(i)));
        List<String> labels = deleteButtons().sendMessage("accessibilityLabel");
        return deleteButtonNamed(labels.get(i));
    }

    private IosView deleteButtonNamed(String name) {
        By selector = By.igor(String.format(DELETE_BUTTON_WITH_ACCESSIBILITY_LABEL, name));
        return view(selector);
    }

    private IosView deleteButtons() {
        return view(DELETE_BUTTONS);
    }

    public void deleteItemAtRow(Integer i) {
        editButton().sendMessage("tap");
        when(itemAtRow(i), is(not(animating())), tap());
        when(deleteButtonAtRow(i), is(tappable()), tap());
        when(confirmDeletionButton(), is(tappable()), tap());
        doneButton().sendMessage("tap");
    }

    private IosView doneButton() {
        return view(DONE_BUTTON);
    }

    private IosView editButton() {
        return view(EDIT_BUTTON);
    }

    private IosView itemAtRow(Integer i) {
        waitUntil(items(), has(size(), greaterThan(i)));
        List<String> itemLabels = items().sendMessage("accessibilityLabel");
        String name = itemLabels.get(i);
        return itemNamed(name);
    }

    private IosView itemNamed(String name) {
        return view(By.igor(String.format(ITEM_WITH_ACCESSIBILITY_LABEL, name)));
    }

    public IosView items() {
        return view(ITEMS);
    }

    public void visitItemAtRow(Integer i) {
        itemAtRow(i).sendMessage("tap");
    }
}
