package com.dhemery.victor.examples.tests;

import com.dhemery.victor.examples.runner.OnMasterPage;
import org.junit.Test;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.examples.views.UITextFieldTextQuery.text;
import static org.hamcrest.Matchers.equalTo;

public class PrefixFieldSetText extends OnMasterPage {
    @Test
    public void setText() {
        assertThat(prefix, has(text(), equalTo("")));
        demo(12);

        beginEditing();
        demo(10);

        prefix.setText("Hello Dolly");
        demo(10);
        assertThat(prefix, has(text(), equalTo("Hello Dolly")));

        prefix.appendText(". Well hello Dolly");
        demo(4);
        assertThat(prefix, has(text(), equalTo("Hello Dolly. Well hello Dolly")));

        prefix.insertText(",", 5);
        demo(2);
        assertThat(prefix, has(text(), equalTo("Hello, Dolly. Well hello Dolly")));

        prefix.insertText(",", 24);
        demo(2);
        assertThat(prefix, has(text(), equalTo("Hello, Dolly. Well hello, Dolly")));

        prefix.appendText(".");
        demo(2);
        assertThat(prefix, has(text(), equalTo("Hello, Dolly. Well hello, Dolly.")));

        endEditing();
        demo(10);
    }
}
