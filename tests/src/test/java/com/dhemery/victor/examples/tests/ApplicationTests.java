package com.dhemery.victor.examples.tests;

import static com.dhemery.victor.view.ViewExtensions.*;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.dhemery.victor.ViewDriver;
import com.dhemery.victor.examples.framework.VictorTest;
import com.dhemery.victor.examples.fixtures.DetailDisplay;
import com.dhemery.victor.examples.fixtures.MasterDisplay;

public class ApplicationTests extends VictorTest {
    private ViewDriver masterView;
	private ViewDriver detailLabel;
	private ViewDriver detailView;
	private ViewDriver masterButton;

	@Before
	public void setUp() {
        MasterDisplay master = new MasterDisplay(application());
        DetailDisplay detail = new DetailDisplay(application());
		masterView = master.masterView();
		detailLabel = master.detailLabel();
		detailView = detail.detailView();
		masterButton = detail.masterButton();
	}

	@Test
	public void navigation() throws IOException {
		when(masterView, is(visible()), flash());
		when(detailLabel, is(visible()), flash());
		detailLabel.call(touch());

		when(detailView, eventually(), is(visible()), flash());
		assertThat(masterButton, eventually(), is(visible()));
		masterButton.call(flash());

		masterButton.call(touch());

		assertThat(masterView, eventually(), is(visible()));
		assertThat(detailLabel, eventually(), is(visible()));
		masterView.call(flash());
		detailLabel.call(flash());
	}
}
