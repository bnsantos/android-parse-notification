package com.bnsantos.parse.pushnotification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private ParseUser mUser;
    private TextView mUsernameEditText;

    private CheckBox mSubscribeProject1;
    private CheckBox mSubscribeProject2;
    private CheckBox mSubscribeProject3;
    private CheckBox mSubscribeProject4;
    private CheckBox mSubscribeProject5;
    private CheckBox mSubscribeProject6;
    private CheckBox mSubscribeProject7;
    private CheckBox mSubscribeProject8;
    private CheckBox mSubscribeProject9;
    private CheckBox mSubscribeProject10;

    private List<ParseObject> mProjects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mUsernameEditText = (TextView) findViewById(R.id.mainUsernameEditText);
        mUser = ((Application) getApplication()).getUSer();

        mUsernameEditText.setText("Logged as: " + mUser.getUsername());

        findViewById(R.id.mainUploadDataBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });

        mSubscribeProject1 = (CheckBox) findViewById(R.id.subscribeCheckbox1);
        mSubscribeProject2 = (CheckBox) findViewById(R.id.subscribeCheckbox2);
        mSubscribeProject3 = (CheckBox) findViewById(R.id.subscribeCheckbox3);
        mSubscribeProject4 = (CheckBox) findViewById(R.id.subscribeCheckbox4);
        mSubscribeProject5 = (CheckBox) findViewById(R.id.subscribeCheckbox5);
        mSubscribeProject6 = (CheckBox) findViewById(R.id.subscribeCheckbox6);
        mSubscribeProject7 = (CheckBox) findViewById(R.id.subscribeCheckbox7);
        mSubscribeProject8 = (CheckBox) findViewById(R.id.subscribeCheckbox8);
        mSubscribeProject9 = (CheckBox) findViewById(R.id.subscribeCheckbox9);
        mSubscribeProject10 = (CheckBox) findViewById(R.id.subscribeCheckbox10);

        findViewById(R.id.subscribeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe();
            }
        });
    }

    private void uploadData() {
        ParseObject project1 = new ParseObject("Project");
        project1.put("name", "Stadium1");
        project1.put("location", "Belo Horizonte");
        project1.saveInBackground();

        ParseObject project2 = new ParseObject("Project");
        project2.put("name", "Hospital1");
        project2.put("location", "Sao Paulo");
        project2.saveInBackground();

        ParseObject project3 = new ParseObject("Project");
        project3.put("name", "OfficeBuilding1");
        project3.put("location", "Rio de Janeiro");
        project3.saveInBackground();

        ParseObject project4 = new ParseObject("Project");
        project4.put("name", "Road1");
        project4.put("location", "Brasilia");
        project4.saveInBackground();

        ParseObject project5 = new ParseObject("Project");
        project5.put("name", "Mall1");
        project5.put("location", "Vitoria");
        project5.saveInBackground();

        ParseObject project6 = new ParseObject("Project");
        project6.put("name", "Museum1");
        project6.put("location", "Porto Alegre");
        project6.saveInBackground();

        ParseObject project7 = new ParseObject("Project");
        project7.put("name", "Road2");
        project7.put("location", "Goias");
        project7.saveInBackground();

        ParseObject project8 = new ParseObject("Project");
        project8.put("name", "OfficeBuilding2");
        project8.put("location", "Salvador");
        project8.saveInBackground();

        ParseObject project9 = new ParseObject("Project");
        project9.put("name", "ResidentialBuilding1");
        project9.put("location", "Coritiba");
        project9.saveInBackground();

        ParseObject project10 = new ParseObject("Project");
        project10.put("name", "Hospital2");
        project10.put("location", "Manaus");
        project10.saveInBackground();

        mProjects = new ArrayList<ParseObject>();
        mProjects.add(project1);
        mProjects.add(project2);
        mProjects.add(project3);
        mProjects.add(project4);
        mProjects.add(project5);
        mProjects.add(project6);
        mProjects.add(project7);
        mProjects.add(project8);
        mProjects.add(project9);
        mProjects.add(project10);
    }

    private void subscribe() {
    }
}
