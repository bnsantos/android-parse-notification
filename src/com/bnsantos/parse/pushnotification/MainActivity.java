package com.bnsantos.parse.pushnotification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
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

    private Spinner mSpinner;

    private EditText mProjectLocationEditText;

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

        mSpinner = (Spinner) findViewById(R.id.projectSpinner);

        mProjectLocationEditText = (EditText) findViewById(R.id.updateProjectLocationEditText);
        findViewById(R.id.updateProjectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProject();
            }
        });
    }

    private void fetchFromParseProjectData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.PROJECT_CLASS);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects != null && parseObjects.size() > 0) {
                    mProjects = parseObjects;
                } else {
                    uploadData();
                }
                changeCheckboxesName();
                fillSpinnerData();

            }
        });
    }

    private void uploadData() {
        ParseACL postACL = new ParseACL(ParseUser.getCurrentUser());
        postACL.setPublicReadAccess(true);
        postACL.setPublicWriteAccess(true);

        ParseObject project1 = new ParseObject("Project");
        project1.put(Constants.PROJECT_NAME_FIELD, "Stadium1");
        project1.put(Constants.PROJECT_LOCATION_FIELD, "Belo Horizonte");
        project1.setACL(postACL);
        project1.saveInBackground();

        ParseObject project2 = new ParseObject("Project");
        project2.put(Constants.PROJECT_NAME_FIELD, "Hospital1");
        project2.put(Constants.PROJECT_LOCATION_FIELD, "Sao Paulo");
        project2.setACL(postACL);
        project2.saveInBackground();

        ParseObject project3 = new ParseObject("Project");
        project3.put(Constants.PROJECT_NAME_FIELD, "OfficeBuilding1");
        project3.put(Constants.PROJECT_LOCATION_FIELD, "Rio de Janeiro");
        project3.setACL(postACL);
        project3.saveInBackground();

        ParseObject project4 = new ParseObject("Project");
        project4.put(Constants.PROJECT_NAME_FIELD, "Road1");
        project4.put(Constants.PROJECT_LOCATION_FIELD, "Brasilia");
        project4.setACL(postACL);
        project4.saveInBackground();

        ParseObject project5 = new ParseObject("Project");
        project5.put(Constants.PROJECT_NAME_FIELD, "Mall1");
        project5.put(Constants.PROJECT_LOCATION_FIELD, "Vitoria");
        project5.setACL(postACL);
        project5.saveInBackground();

        ParseObject project6 = new ParseObject("Project");
        project6.put(Constants.PROJECT_NAME_FIELD, "Museum1");
        project6.put(Constants.PROJECT_LOCATION_FIELD, "Porto Alegre");
        project6.setACL(postACL);
        project6.saveInBackground();

        ParseObject project7 = new ParseObject("Project");
        project7.put(Constants.PROJECT_NAME_FIELD, "Road2");
        project7.put(Constants.PROJECT_LOCATION_FIELD, "Goias");
        project7.setACL(postACL);
        project7.saveInBackground();

        ParseObject project8 = new ParseObject("Project");
        project8.put(Constants.PROJECT_NAME_FIELD, "OfficeBuilding2");
        project8.put(Constants.PROJECT_LOCATION_FIELD, "Salvador");
        project8.setACL(postACL);
        project8.saveInBackground();

        ParseObject project9 = new ParseObject("Project");
        project9.put(Constants.PROJECT_NAME_FIELD, "ResidentialBuilding1");
        project9.put(Constants.PROJECT_LOCATION_FIELD, "Coritiba");
        project9.setACL(postACL);
        project9.saveInBackground();

        ParseObject project10 = new ParseObject("Project");
        project10.put(Constants.PROJECT_NAME_FIELD, "Hospital2");
        project10.put(Constants.PROJECT_LOCATION_FIELD, "Manaus");
        project10.setACL(postACL);
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
        installation.addAll(Constants.INSTALLATION_PROJECTS, getProjectIdsToSubscribe());
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

    private void fillSpinnerData() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getProjectNames());
        mSpinner.setAdapter(spinnerArrayAdapter);
    }

    private List<String> getProjectNames() {
        List<String> projectNames = new ArrayList<String>();
        if (mProjects != null) {
            for (ParseObject o : mProjects) {
                projectNames.add(o.getString(Constants.PROJECT_NAME_FIELD));
            }
        }
        return projectNames;
    }

    private void changeCheckboxesName() {
        for (int i = 0; i < mSubscribeProjectsCheckbox.size(); i++) {
            mSubscribeProjectsCheckbox.get(i).setText(mProjects.get(i).getString(Constants.PROJECT_NAME_FIELD));
        }
    }

    private void updateProject() {
        final ParseObject project = mProjects.get(mSpinner.getSelectedItemPosition());
        project.put(Constants.PROJECT_LOCATION_FIELD, mProjectLocationEditText.getText().toString());
        project.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //Not calling PushNotifications from mobile
                    /*HashMap<String, Object> params = new HashMap<String, Object>();
                    params.put(Constants.PUSH_NOTIFICATIONS_PARAM_USER_ID, mUser.getObjectId());
                    params.put(Constants.PUSH_NOTIFICATIONS_PARAM_PROJECTS_ID, project.getObjectId());

                    ParseCloud.callFunctionInBackground("pushNotifications", params, new FunctionCallback<String>() {
                        @Override
                        public void done(String s, ParseException e) {
                            //TODO
                        }
                    });*/
                } else {
                    Toast.makeText(getApplicationContext(), "Error updating project", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
