package com.example.user.mad_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.mad_new.Staff_Login;

public class MainActivity extends AppCompatActivity {


    //hello
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
public void selectview(){
        startActivity(new Intent(this, Staff_Login.class));
}

}
