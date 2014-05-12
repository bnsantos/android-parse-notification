package com.bnsantos.parse.pushnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.parse.*;

/**
 * Created by bruno on 4/4/14.
 */
public class SigningActivity extends Activity {

    private EditText mEmail;
    private EditText mPassword;

    private LinearLayout mLoadingLayout;
    private LinearLayout mContentLayout;

    private ProgressSpinner mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseAnalytics.trackAppOpened(getIntent());

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

        mLoadingLayout = (LinearLayout) findViewById(R.id.loginLoadingLayout);
        mContentLayout = (LinearLayout) findViewById(R.id.loginContentLayout);

        mProgress = new ProgressSpinner(mLoadingLayout, mContentLayout, getResources().getInteger(android.R.integer.config_shortAnimTime));
    }

    private void login() {
        mProgress.show(true);
        ParseUser.logInInBackground(mEmail.getText().toString(), mPassword.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {

                    ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                    installation.put(Constants.USER_ID, user.getObjectId());
                    installation.saveInBackground();

                    ((Application) getApplication()).setUSer(user);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    mProgress.show(false);
                    Toast.makeText(getApplicationContext(), "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn() {
        mProgress.show(true);
        ParseUser user = new ParseUser();
        user.setEmail(mEmail.getText().toString());
        user.setUsername(mEmail.getText().toString());
        user.setPassword(mPassword.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    login();
                } else {
                    mProgress.show(false);
                    Toast.makeText(getApplicationContext(), "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
