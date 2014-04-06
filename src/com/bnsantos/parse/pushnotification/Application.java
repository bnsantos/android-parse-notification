package com.bnsantos.parse.pushnotification;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

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
        Parse.initialize(this, "D6fMbuKco7vMuIEeZ030UGoW2JbQx43uhtsJXaNz", "lYBDbZ1dG5R67S46y0m4qleO0MVHrjgBtpdoDzrJ");

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        /*// Specify an Activity to handle all pushes by default.
        PushService.setDefaultPushCallback(this, MyActivity.class);

        IntentFilter intentFilter = new IntentFilter("MyAction");
        BroadcastReceiver pushReceiver;
        pushReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                String message = extras != null ? extras.getString("com.parse.Data") : "";
                JSONObject jObject;
                try {
                    jObject = new JSONObject(message);
                    Toast toast = Toast.makeText(context, jObject.getString("alert")+ jObject.getString("title")+jObject.getString("action"), Toast.LENGTH_SHORT);
                    toast.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        registerReceiver(pushReceiver, intentFilter);*/
    }

    public ParseUser getUSer() {
        return mUSer;
    }

    public void setUSer(ParseUser mUSer) {
        this.mUSer = mUSer;
    }
}
