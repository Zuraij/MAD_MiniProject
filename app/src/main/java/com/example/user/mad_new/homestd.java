package com.example.user.mad_new;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class homestd extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    EditText ssFirstName , ssLastName , ssAge , ssAddress , ssEmailID ,ssContactNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ssFirstName = (EditText)findViewById(R.id.ssFirstName);
        ssLastName = (EditText)findViewById(R.id.ssLastName);
        ssAge = (EditText)findViewById(R.id.ssAge);
        ssAddress = (EditText)findViewById(R.id.ssAddress);
        ssContactNumber = (EditText)findViewById(R.id.ssContactNumber);

        ssEmailID = (EditText)findViewById(R.id.ssEmailID);

        bLogout = (Button)findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bLogout:

                startActivity(new Intent(this,LoginStudent.class));


                break;
        }
    }
}

