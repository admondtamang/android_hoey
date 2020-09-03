package com.Softwarica.hoey;

import android.widget.Toast;

import com.Softwarica.hoey.Token.LoginToken;
import com.Softwarica.hoey.UI.Login;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    @Test
    public void empty_validation() {
        Login login=new Login();
        boolean result = login.validInput("", "");
        assertEquals(false, result);
    }

    @Test
    public void user_can_login_or_not() {
        LoginToken loginToken = new LoginToken();
        boolean result = loginToken.checkUser("admond", "tamang");
        assertEquals(true, result);
    }
}
