package com.example.user.mad_new;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Staff_Login extends AppCompatActivity {
    EditText editname,editpass;
    Button logbtn;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__login);
        dbHelper=new DatabaseHelper(this);


        editname=findViewById(R.id.usernameL);
        editpass=findViewById(R.id.passwordL);
        logbtn=findViewById(R.id.loginbtn);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editname.getText().toString();
                String pass = editpass.getText().toString();

                boolean checklogin = dbHelper.loginfun(username, pass);




                if (username.isEmpty()) {
                    editname.setError("Username can't be Empty");
                    editname.requestFocus();
                    return;
                }
                if (pass.isEmpty()) {
                    editpass.setError("Password can't be Empty");
                    editpass.requestFocus();
                    return;
                }


                if (checklogin == true) {
                    Toasty.success(getApplicationContext(), "Logged In Successfully", Toast.LENGTH_SHORT, true).show();

                    //Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Staff_Login.this, Adminpanel_Activity.class));
                }

                else
                    Toasty.error(getApplicationContext(), "Invalid Credentials. Check Username or Password", Toast.LENGTH_LONG, true).show();

                // Toast.makeText(getApplicationContext(), "Invalid Please insert correctly", Toast.LENGTH_SHORT).show();


            }



        });
    }



    public void getsgotostudentlogin(View view) {
        startActivity(new Intent(this,LoginStudent.class));

    }


}
