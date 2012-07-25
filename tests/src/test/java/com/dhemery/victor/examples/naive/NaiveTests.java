package com.dhemery.victor.examples.naive;

import com.dhemery.polling.Query;
import com.dhemery.victor.By;
import com.dhemery.victor.examples.pages.MasterPage;
import com.dhemery.victor.examples.runner.VigorTest;
import com.dhemery.victor.examples.views.UILabel;
import com.dhemery.victor.examples.views.UISwitch;
import com.dhemery.victor.examples.views.UITextField;
import com.dhemery.victor.examples.views.UIView;
import org.junit.Before;
import org.junit.Test;

import static com.dhemery.polling.Has.has;
import static com.dhemery.victor.Igor.igor;
import static org.hamcrest.Matchers.equalTo;

public class NaiveTests extends VigorTest {
    private static final String BLUE = "{\"green\":1,\"red\":0,\"alpha\":1,\"blue\":0}";
    private static final String GREEN = "{\"green\":0,\"red\":0,\"alpha\":1,\"blue\":1}";
    private static final String RED = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":0}";
    private static final String YELLOW = "{\"green\":0,\"red\":1,\"alpha\":1,\"blue\":1}";

    MasterPage master;
    private UITextField prefix;
    private UILabel preview;
    private UISwitch prefixEnabled;
    private UIView increment;
    private boolean enabled = true;

    @Before
    public void setup() {
        prefix = textField(igor("#prefix"));
        preview = label(igor("#nextItemPreview"));
        prefixEnabled = toggle(igor("#prefixEnabled"));
        increment = button(igor("[accessibilityLabel=='Increment']"));
    }

    @Test
    public void backgroundColorFollowsPrefixFieldEditMode() {
        assertThat(preview, has(backgroundColor(), equalTo(GREEN)));
        demo(12);

        beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));
        demo(8);

        endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));
        demo(3);

        beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));
        demo(1);

        endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));
        demo(1);

        beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));
        demo(1);

        endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));
        demo(1);

        beginEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(YELLOW)));
        demo(1);

        endEditing();
        assertThat(preview, eventually(), has(backgroundColor(), equalTo(GREEN)));
        demo(1);
    }

    @Test
    public void textColorFollowsPrefixEnabledSwitch() {
        assertThat(preview, has(textColor(), equalTo(BLUE)));
        demo(9);

        togglePrefixSwitch();
        assertThat(preview, eventually(), has(textColor(), equalTo(RED)));
        demo(5);

        togglePrefixSwitch();
        assertThat(preview, eventually(), has(textColor(), equalTo(BLUE)));
        demo(1);

        togglePrefixSwitch();
        assertThat(preview, eventually(), has(textColor(), equalTo(RED)));
        demo(1);

        togglePrefixSwitch();
        assertThat(preview, eventually(), has(textColor(), equalTo(BLUE)));
        demo(1);

        togglePrefixSwitch();
        assertThat(preview, eventually(), has(textColor(), equalTo(RED)));
        demo(1);

        togglePrefixSwitch();
        assertThat(preview, eventually(), has(textColor(), equalTo(BLUE)));
        demo(1);
    }

    private void togglePrefixSwitch() {
        increment.tap();
        enabled = !enabled;
        prefixEnabled.sendMessage("setOn:animated:", enabled, true);
        waitForAnimation();
    }

    private void waitForAnimation() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    private void endEditing() {
        prefix.sendMessage("setText:", "Done editing ");
        prefix.sendMessage("resignFirstResponder");
    }

    private void beginEditing() {
        prefix.sendMessage("becomeFirstResponder");
        prefix.sendMessage("setText:", "Editing ");
    }

    private UISwitch toggle(By query) {
        return new UISwitch(viewFactory, query);
    }

    public UITextField textField(By query) {
        return new UITextField(viewFactory, query);
    }

    public UILabel label(By query) {
        return new UILabel(viewFactory, query);
    }

    private UIView button(By query) {
        return new UIView(viewFactory, query);
    }

    private Query<UIView, String> backgroundColor() {
        return new Query<UIView,String>(){
            @Override
            public String name() {
                return "background color";
            }

            @Override
            public String query(UIView view) {
                return view.backgroundColor().get(0);
            }
        };
    }

    private Query<UILabel, String> textColor() {
        return new Query<UILabel, String>() {
            @Override
            public String name() {
                return "text color";
            }

            @Override
            public String query(UILabel label) {
                return label.textColor().get(0);
            }
        };
    }
}
