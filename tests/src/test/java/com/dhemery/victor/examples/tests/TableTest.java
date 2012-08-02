package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.OnVigorApp;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UIViewQueries.count;
import static org.hamcrest.Matchers.is;

public class TableTest extends OnVigorApp {
    private MasterPage master;

    @Before
    public void setUp() {
        master = new MasterPage(application(), eventually());
    }

    @Test
    public void aNewlyLaunchedVigorHasNoItems() {
        assertThat(master.items(), count(), is(0));
    }

    @Test
    public void aNewItemAppearsInTheMasterPage() {
        master.addItem();
        assertThat(master.items(), count(), eventually(), is(1));
    }

    @Test
    public void aDeletedItemDoesNotAppearInTheMasterPage() {
        master.addItem();
        master.deleteItem(0);
        assertThat(master.items(), count(), is(0));
    }

    @Test
    public void aNewItemHasADetailPage() {
        master.addItem();
        master.visitItem(0);
    }
}
