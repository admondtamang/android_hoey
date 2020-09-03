package com.Softwarica.hoey;

import com.Softwarica.hoey.Model.User;
import com.Softwarica.hoey.UI.Signup;
import com.Softwarica.hoey.Utils.EmailValidator;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SignUp {

    @Test
    public void user_can_signUp_or_not() {
        Signup signup = new Signup();
        User user = new User("addu", "admondt@gmail.com", "password");
        Boolean result = signup.SignUpToken(user);
        assertEquals(true, result);
    }

    @Test
    public void email_valid() {

        assertTrue(EmailValidator.isValidEmail("admondtamang@email.com"));
        assertTrue(EmailValidator.isValidEmail("admond@email.co.uk"));
    }

    @Test
    public void email_invalid() {
        assertFalse(EmailValidator.isValidEmail("admond@email"));
        assertFalse(EmailValidator.isValidEmail("admond@email..com"));
        assertFalse(EmailValidator.isValidEmail("@email.com"));
        assertFalse(EmailValidator.isValidEmail(""));
        assertFalse(EmailValidator.isValidEmail(null));
    }

}
