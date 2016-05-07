package com.kwasen.bustime;

/*
* Created by: Mabweh Isaac [2016] [UNLICENSED]
* Created for: Final Year Project
* Created under: Influence of various liquids
* Description: An application that shows where the bus is as well as
*              how long it will take to arrive at requesting passengers location/bus stop
* */

//libraries and importations --those little things

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.dd.sample.utils.ProgressGenerator;

public class LoginActivity extends Activity
        implements ProgressGenerator.OnCompleteListener {


    //usual variable declaration
    Button logInButton,signUpButton;
    EditText userName,userPassword;

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //linking attribute elements to be usable in java
        userName = (EditText)findViewById(R.id.editText1);
        userPassword = (EditText)findViewById(R.id.editText2);
        signUpButton = (Button)findViewById(R.id.button2);
        //logInButton = (Button)findViewById(R.id.button1);
        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        final ActionProcessButton logInButton = (ActionProcessButton) findViewById(R.id.button1);
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.getBoolean(EXTRAS_ENDLESS_MODE)) {
            logInButton.setMode(ActionProcessButton.Mode.ENDLESS);
        } else {
            logInButton.setMode(ActionProcessButton.Mode.PROGRESS);
        }

        //trigger action when buttons are clicked
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //some method to do something when log in button is clicked

                ActionProcessButton btn = (ActionProcessButton) view;

                if((userName.getText().toString().equals("admin1")
                        && userPassword.getText().toString().equals("admin1")) || (userName.getText().toString().equals("admin2")
                        && userPassword.getText().toString().equals("admin2"))) {
                    Toast.makeText(getApplicationContext(), "Log In successful\n Redirecting...",
                            Toast.LENGTH_SHORT).show();
                    //userName.setEnabled(false);
                    //userPassword.setEnabled(false);

                    progressGenerator.start(logInButton);
                    logInButton.setEnabled(false);
                    signUpButton.setEnabled(false);
                    userName.setEnabled(false);
                    userPassword.setEnabled(false);

                    //start another activity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials. Try Again",
                            Toast.LENGTH_SHORT).show();
                    //userName.setEnabled(true);
                    //userPassword.setEnabled(true);
                    progressGenerator.start(logInButton);
                    logInButton.setEnabled(true);
                    userName.setEnabled(true);
                    userPassword.setEnabled(true);
                }
            };
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //some method to do something when sign up button is clicked
                Toast.makeText(getApplicationContext(), "coming soon",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onComplete() {
        Toast.makeText(this, R.string.Loading_Complete, Toast.LENGTH_LONG).show();
        //finish();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        return false;
    }

}
