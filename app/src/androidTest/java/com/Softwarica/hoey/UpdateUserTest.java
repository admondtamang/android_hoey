package com.Softwarica.hoey;
import androidx.test.rule.ActivityTestRule;

import com.Softwarica.hoey.UI.Signup;
import com.Softwarica.hoey.UI.UpdateProfile;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class UpdateUserTest {
    @Rule
    public ActivityTestRule<UpdateProfile> UpdateActivityTestRule=
            new ActivityTestRule<>(UpdateProfile.class);

    @Test
    public void TestUpdateUser() throws Exception{
        onView(withId(R.id.etUsername))
                .perform(typeText("ram"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.etEmail))
                .perform(typeText("ram@gmail.com"))
                .perform(closeSoftKeyboard());


    }

}
