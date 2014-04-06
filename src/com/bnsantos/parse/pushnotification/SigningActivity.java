package com.bnsantos.parse.pushnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by bruno on 4/4/14.
 */
public class SigningActivity extends Activity {

    private EditText mEmail;
    private EditText mPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.signIngBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void login() {
        ParseUser.logInInBackground(mEmail.getText().toString(), mPassword.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    ((Application) getApplication()).setUSer(user);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    //TODO
                }
            }
        });
    }

    private void signIn() {
        ParseUser user = new ParseUser();
        user.setEmail(mEmail.getText().toString());
        user.setUsername(mEmail.getText().toString());
        user.setPassword(mPassword.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    //TODO
                } else {
                    login();
                }
            }
        });
    }
}
