package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.VigorTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.extensions.UIViewCountQuery.count;
import static org.hamcrest.Matchers.equalTo;

public class ApplicationTests extends VigorTest {
    Logger log = LoggerFactory.getLogger(getClass());
    private MasterPage master;

    @Before
    public void setUp() {
        master = new MasterPage(application, timer);
    }

    @After
    public void removeAllItems() {
        master.deleteAllCells();
    }

    @Test
    public void aNewlyLaunchedVigorHasNoItems() {
        assertThat(master.items(), has(count(), equalTo(0)));
    }

    @Test
    public void aNewItemAppearsInTheMasterPage() {
        master.addCell();
        assertThat(master.items(), has(count(), equalTo(1)));
    }

    @Test
    public void aDeletedItemDoesNotAppearInTheMasterPage() {
        master.addCell();
        master.deleteItem(0);
        assertThat(master.items(), has(count(), equalTo(0)));
    }

    @Test
    public void aNewItemHasADetailPage() {
        master.addCell();
        master.visitCell(0);
    }
}
