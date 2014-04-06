package com.bnsantos.parse.pushnotification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.parse.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private ParseUser mUser;
    private TextView mUsernameEditText;

    private List<ParseObject> mProjects;
    private List<CheckBox> mSubscribeProjectsCheckbox;

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
                fetchFromParseProjectData();
            }
        });

        mSubscribeProjectsCheckbox = new ArrayList<CheckBox>();
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox1));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox2));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox3));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox4));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox5));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox6));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox7));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox8));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox9));
        mSubscribeProjectsCheckbox.add((CheckBox) findViewById(R.id.subscribeCheckbox10));

        findViewById(R.id.subscribeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe();
            }
        });
    }

    private void fetchFromParseProjectData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Project");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects != null && parseObjects.size() > 0) {
                    mProjects = parseObjects;
                } else {
                    uploadData();
                }
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
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.addAll("projects", getProjectIdsToSubscribe());
        installation.saveInBackground();
    }

    private List<String> getProjectIdsToSubscribe() {
        List<String> projectsId = new ArrayList<String>();
        for (int i = 0; i < mSubscribeProjectsCheckbox.size(); i++) {
            CheckBox c = mSubscribeProjectsCheckbox.get(i);
            if (c.isChecked()) {
                projectsId.add(mProjects.get(i).getObjectId());
            }
        }
        return projectsId;
    }

}
