package com.example.user.mad_new;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginStudent extends AppCompatActivity {
    EditText editTextuser,editTextpass;
    Button login;
    DatabaseHelperStd db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        db=new DatabaseHelperStd(this);



        editTextpass=findViewById(R.id.ssPassword);
        editTextuser=findViewById(R.id.ssUsername);
        login=findViewById(R.id.bLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=editTextuser.getText().toString();
                String pass=editTextpass.getText().toString();

                boolean checkstudent=db.LoginStudent(username,pass);
                if (checkstudent==true){
                    Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginStudent.this, ViewNoticeStudent.class));

                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Credentials. Check Username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
