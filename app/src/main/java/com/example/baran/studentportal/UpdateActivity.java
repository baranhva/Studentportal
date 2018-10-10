package com.example.baran.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    EditText mPortalURL;
    EditText mPortalTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init local variables
         mPortalURL = findViewById(R.id.editText_url);
         mPortalTitle = findViewById(R.id.editText_title);




        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the user text from the textfield
                String url = mPortalURL.getText().toString();
                String title = mPortalTitle.getText().toString();

                //Check if some text has been added
                if (!(TextUtils.isEmpty(url))&&!(TextUtils.isEmpty(title))) {
                    //Initialize the EditText for the next item
                    mPortalURL.setText("");
                    mPortalTitle.setText("");

                    //Prepare the return parameter and return
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    Portal newportal = new Portal(url, title);
                    intent.putExtra(MainActivity.EXTRA_PORTAL, newportal);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    //Show a message to the user if the textfield is empty
                    Snackbar.make(view, "Please enter some text in the textfields", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }


//
//
//                String url = mPortalView.getText().toString();
//                String title = mPortalTitle.getText().toString();
//
////(portalUpdate.setmPortalText(updatedReminderText)));
//                if (!TextUtils.isEmpty(url)&&!TextUtils.isEmpty(title)) {
//                    portalUpdate.setmPortalURL(url);
//                    portalUpdate.setmPortalTitle(title);
//                    //Prepare the return parameter and return
//                    Intent resultIntent = new Intent();
//                    resultIntent.putExtra(MainActivity.EXTRA_PORTAL, portalUpdate);
//                    setResult(Activity.RESULT_OK, resultIntent);
//                    finish();
//                } else {
//                    Snackbar.make(view, "Enter some data", Snackbar.LENGTH_LONG);
//                }
//






            }
        });
    }

}
