package com.softwarica.hoeywearos.Token;
import com.softwarica.hoeywearos.Api.Url;
import com.softwarica.hoeywearos.Api.UserApi;
import com.softwarica.hoeywearos.serverresponse.UserResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginToken {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<UserResponse> usersCall=usersAPI.checkUser(username,password);

        try {
            Response<UserResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
