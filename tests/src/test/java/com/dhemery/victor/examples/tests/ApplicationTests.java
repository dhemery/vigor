package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.VictorTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.extensions.ViewTapAction.tap;
import static com.dhemery.victor.examples.extensions.ViewListEmptyMatcher.empty;
import static com.dhemery.victor.examples.extensions.ViewListSizeQuery.size;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ApplicationTests extends VictorTest {
    private MasterPage master;

    @Before
    public void setUp() {
        master = new MasterPage(application, timer);
    }

    @After
    public void removeAllItems() {
        master.deleteAllItems();
    }

    @Test
    public void aNewlyLaunchedVigorHasNoItems() {
        assertThat(master.items(), has(size(), equalTo(0)));
    }

    @Test
    public void aNewItemAppearsInTheMasterPage() {
        master.addItem();
        assertThat(master.items(), has(size(), equalTo(1)));
    }

    @Test
    public void aDeletedItemDoesNotAppearInTheMasterPage() {
        master.addItem();
        master.deleteItemAtRow(0);
        assertThat(master.items(), is(empty()));
    }

    @Test
    public void aNewItemHasADetailPage() {
        master.addItem();
        master.visitItemAtRow(0);
    }
}
