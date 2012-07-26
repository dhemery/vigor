package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.victor.examples.views.UITextFieldEditingMatcher.editing;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class PrefixFieldAllowEditing extends OnMasterPage {
    @Test
    public void editingIsAllowedWhenPrefixIsEnabled() {
        enablePrefix();
        demo(7);

        beginEditing();
        demo(4);
        assertThat(prefix, is(editing()));
    }

    @Test
    public void editingIsDisallowedWhenPrefixIsEnabled() {
        disablePrefix();
        demo(4);

        beginEditing();
        demo(3);
        assertThat(prefix, is(not(editing())));
    }
}
