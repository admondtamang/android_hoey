package com.Softwarica.hoey;
import androidx.test.rule.ActivityTestRule;

import com.Softwarica.hoey.Utils.commentUpdate;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
public class UpdateCommentTest {
     @Rule
    public ActivityTestRule<commentUpdate> activity_loginActivityTestRule = new ActivityTestRule<>(commentUpdate.class);

    @Test
    public void updateComment() {
        onView(withId(R.id.etCommentUpdate)).perform(typeText("Good song"));
        closeSoftKeyboard();
        onView(withId(R.id.btnMessageUpdate)).perform(click());
    }
}
