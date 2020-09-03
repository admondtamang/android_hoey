package com.Softwarica.hoey;


import androidx.test.rule.ActivityTestRule;

import com.Softwarica.hoey.Utils.CommentActivity;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
public class CommentTest {
 @Rule
    public ActivityTestRule<CommentActivity> activity_loginActivityTestRule = new ActivityTestRule<>(CommentActivity.class);

    @Test
    public void commentTest() {
        onView(withId(R.id.etComment)).perform(typeText("Good song"));
        closeSoftKeyboard();
//        onView(withId(R.id.btnAddComment)).perform(click());
    }
}
