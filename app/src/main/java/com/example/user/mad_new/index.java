package com.example.user.mad_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void gotostf(View view) {
        startActivity(new Intent(this,Staff_Login.class));
    }

    public void gotostd(View view) {
        startActivity(new Intent(this,LoginStudent.class));
    }
}
