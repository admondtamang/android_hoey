package com.Softwarica.hoey;
import androidx.test.rule.ActivityTestRule;

import com.Softwarica.hoey.UI.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginTest {

     @Rule
    public ActivityTestRule<Login> activity_loginActivityTestRule = new ActivityTestRule<>(Login.class);

    @Test
    public void logintest() {
        onView(withId(R.id.etUsername)).perform(typeText("admond"));
        closeSoftKeyboard();
        onView(withId(R.id.etPassword)).perform(typeText("tamang"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogin)).perform(click());
    }
}
