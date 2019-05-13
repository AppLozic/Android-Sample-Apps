package com.android.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acitivty);
        setTitle("Login");


         final EditText userIdText =  findViewById(R.id.userIdEditText);
        final EditText password =  findViewById(R.id.password);

        Button loginButton =  findViewById(R.id.chatButton);

        if(userIdText.getText().length() < 0){

          return;
       }

        if(password.getText().length() < 0){
            return;
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
