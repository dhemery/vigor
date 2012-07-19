package com.dhemery.victor.examples.pages;

import com.dhemery.polling.PollTimer;
import com.dhemery.victor.By;
import com.dhemery.victor.IosViewFactory;
import com.dhemery.victor.examples.views.UITableViewCell;
import com.dhemery.victor.examples.views.UIView;

import java.util.List;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.Igor.igor;
import static com.dhemery.victor.examples.views.UIViewAnimatingMatcher.animating;
import static com.dhemery.victor.examples.views.UIViewCountQuery.count;
import static com.dhemery.victor.examples.views.UIViewVisibleMatcher.visible;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class MasterPage extends Page {
    private static final By ADD_BUTTON = igor("UINavigationButton[accessibilityLabel=='Add']");
    private static final By DONE_BUTTON = igor("UINavigationButton[accessibilityLabel=='Done']");
    private static final By EDIT_BUTTON = igor("UINavigationButton[accessibilityLabel=='Edit']");


    private static final By CELL = igor("UITableViewCell*");
    private static final By CELL_LABEL = igor(CELL.pattern() + " UILabel");
    private static final String CELL_WITH_LABEL = "(" + CELL_LABEL.pattern() + "[accessibilityLabel=='%s'])";

    public MasterPage(IosViewFactory application, PollTimer timer) {
        super(application, timer);
    }

    public void addItem() {
        view(ADD_BUTTON).tap();
    }

    public UITableViewCell items() {
        return cell(CELL);
    }

    public void deleteAllItems() {
        while (the(items(), has(count(), greaterThan(0)))) {
            deleteItem(0);
        }
    }

    public void deleteItem(Integer i) {
        editButton().tap();
        UITableViewCell item = item(i);
        waitUntil(item, is(not(animating())));
        item.delete();
        waitUntil(item, is(not(visible())));
        doneButton().tap();
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
        waitUntil(items(), has(count(), greaterThan(i)));
        return itemLabels().get(i);
    }

    private List<String> itemLabels() {
        return view(CELL_LABEL).sendMessage("accessibilityLabel");
    }

    private UITableViewCell itemWithLabel(String label) {
        return cell(igor(String.format(CELL_WITH_LABEL, label)));
    }

    private UITableViewCell cell(By query) {
        return new UITableViewCell(application(), query);
    }

    public void visitItem(Integer i) {
        item(i).tap();
    }
}
