package com.applozic.sample.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.api.account.register.RegistrationResponse;
import com.applozic.mobicomkit.api.account.user.User;
import com.applozic.mobicomkit.listners.AlLoginHandler;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acitivty);
        setTitle("Login");

        final EditText userIdText = findViewById(R.id.userIdEditText);
        final EditText password = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.chatButton);

        if (userIdText.getText().length() < 0) {
            Log.i("UserId", "Is empty");
            return;
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userIdText.getText().toString(), password.getText().toString());
            }
        });

    }

    //Login code of applozic
    void login(String userId, String password) {
        User user = new User();
        user.setUserId(userId); //userId it can be any unique user identifier NOTE : +,*,? are not allowed chars in userId.
        user.setAuthenticationTypeId(User.AuthenticationType.APPLOZIC.getValue());  //User.AuthenticationType.APPLOZIC.getValue() for password verification from Applozic server and User.AuthenticationType.CLIENT.getValue() for access Token verification from your server set access token as password
        user.setPassword(password); //optional, leave it blank for testing purpose, read this if you want to add additional security by verifying password from your server https://www.applozic.com/docs/configuration.html#access-token-url
        user.setImageLink("");//optional, set your image link if you have

        Applozic.connectUser(this, user, new AlLoginHandler() {
            @Override
            public void onSuccess(RegistrationResponse registrationResponse, Context context) {
                // After successful registration with Applozic server the callback will come here
                Intent mainIntent = new Intent(context, MainActivity.class);
                context.startActivity(mainIntent);
                LoginActivity.this.finish();
            }

            @Override
            public void onFailure(RegistrationResponse registrationResponse, Exception exception) {
                // If any failure in registration the callback  will come here
                Toast.makeText(LoginActivity.this, "Error : " + registrationResponse, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
