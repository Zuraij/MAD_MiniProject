package com.example.user.mad_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Modify_Staff_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEditText,emailEditText,firstnameEditText,lastnameEditText,positionEditText,
            addressEditText,phoneEditText,genderEditText,salaryEditText,passwordEditText ;


    private long _id;

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify__staff_);


        dbManager = new DBManager(this);
        dbManager.open();

        usernameEditText = (EditText) findViewById(R.id.usernameupdate);
        emailEditText = (EditText) findViewById(R.id.emailupdate);
        firstnameEditText = (EditText) findViewById(R.id.firstnameupdate);
        lastnameEditText = (EditText) findViewById(R.id.lastnameupdate);
        positionEditText = (EditText) findViewById(R.id.positionupdate);
        addressEditText = (EditText) findViewById(R.id.addressupdate);
        genderEditText = (EditText) findViewById(R.id.genderupdate);
        phoneEditText = (EditText) findViewById(R.id.phoneupdate);
        salaryEditText = (EditText) findViewById(R.id.salaryupdate);
        passwordEditText = (EditText) findViewById(R.id.passwordupdate);
        Button updateBtn = (Button) findViewById(R.id.btn_update);
        Button deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String user = intent.getStringExtra("user");
        String email = intent.getStringExtra("email");
        String first = intent.getStringExtra("first");
        String last = intent.getStringExtra("last");
        String posi = intent.getStringExtra("position");
        String addres = intent.getStringExtra("address");
        String gen = intent.getStringExtra("gender");
        String pho = intent.getStringExtra("phone");
        String sal = intent.getStringExtra("salary");
        String pass = intent.getStringExtra("pass");



        _id = Long.parseLong(id);

        usernameEditText.setText(user);
        emailEditText.setText(email);
        firstnameEditText.setText(first);
        lastnameEditText.setText(last);
        positionEditText.setText(posi);
        addressEditText.setText(addres);
        genderEditText.setText(gen);
        phoneEditText.setText(pho);
        salaryEditText.setText(sal);
        passwordEditText.setText(pass);


        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String firstname = firstnameEditText.getText().toString();
                String lastname = lastnameEditText.getText().toString();
                String poss= positionEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String gender = genderEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String salary = salaryEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (username.isEmpty()) {
                    usernameEditText.setError("Username can't be Empty");
                    usernameEditText.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    emailEditText.setError("Email can't be Empty");
                    emailEditText.requestFocus();
                    return;
                }
                if (firstname.isEmpty()) {
                    firstnameEditText.setError("Username can't be Empty");
                    firstnameEditText.requestFocus();
                    return;
                }
                if (lastname.isEmpty()) {
                    lastnameEditText.setError("Lastname can't be Empty");
                    lastnameEditText.requestFocus();
                    return;
                }
                if (poss.isEmpty()) {
                    positionEditText.setError("Position can't be Empty");
                    positionEditText.requestFocus();
                    return;
                }
                if (address.isEmpty()) {
                    addressEditText.setError("Address can't be Empty");
                    addressEditText.requestFocus();
                    return;
                }
                if (gender.isEmpty()) {
                    genderEditText.setError("Gender can't be Empty");
                    genderEditText.requestFocus();
                    return;
                }
                if (phone.isEmpty()) {
                    phoneEditText.setError("Phone can't be Empty");
                    phoneEditText.requestFocus();
                    return;
                }
                if (salary.isEmpty()) {
                    salaryEditText.setError("Salary can't be Empty");
                    salaryEditText.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    passwordEditText.setError("password can't be Empty");
                    passwordEditText.requestFocus();
                    return;
                }

                dbManager.update(_id, username,email,firstname,lastname,poss,address,gender,Integer.parseInt(phone),Double.parseDouble(salary),password);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), View_List_Activity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
