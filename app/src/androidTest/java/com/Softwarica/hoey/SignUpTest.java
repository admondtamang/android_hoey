package com.Softwarica.hoey;

import androidx.test.rule.ActivityTestRule;

import com.Softwarica.hoey.UI.Signup;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
public class SignUpTest {


    @Rule
    public ActivityTestRule<Signup> SignUpActivityTestRule=
            new ActivityTestRule<>(Signup.class);

    @Test
    public void TestSignup() throws Exception{
        onView(withId(R.id.etUsername))
                .perform(typeText("admond"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.etEmail))
                .perform(typeText("admondtamang@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.etPassword))
                .perform(typeText("tamang"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.btnSignup))
                .perform(click());


    }
}
