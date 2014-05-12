package com.bnsantos.parse.pushnotification;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.parse.PushService;

/**
 * Created by bruno on 4/4/14.
 */
public class Application extends android.app.Application {
    public Application() {
    }

    private ParseUser mUSer;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the Parse SDK.
        Parse.initialize(this, "YOUR_APP_ID", "YOUR_CLIENT_KEY");

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        // Specify an Activity to handle all pushes by default.
        PushService.setDefaultPushCallback(this, MainActivity.class);
    }

    public ParseUser getUSer() {
        return mUSer;
    }

    public void setUSer(ParseUser mUSer) {
        this.mUSer = mUSer;
    }
}
