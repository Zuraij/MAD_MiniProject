package com.example.user.mad_new;

import android.content.Intent;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ADD_staff_activity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEditText, emailEditText, firstnameEditText, lastnameEditText, positionEditText,
            addressEditText, phoneEditText, genderEditText, salaryEditText, passwordEditText;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_activity);
        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        emailEditText = (EditText) findViewById(R.id.email_edittext);
        firstnameEditText = (EditText) findViewById(R.id.firstname_edittext);
        lastnameEditText = (EditText) findViewById(R.id.lastname_edittext);
        positionEditText = (EditText) findViewById(R.id.position_edittext);
        addressEditText = (EditText) findViewById(R.id.address_edittext);
        genderEditText = (EditText) findViewById(R.id.gender_edittext);
        phoneEditText = (EditText) findViewById(R.id.phone_edittext);
        salaryEditText = (EditText) findViewById(R.id.salary_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        Button addTodoBtn = (Button) findViewById(R.id.add_record);

        //opening database
        dbManager = new DBManager(this);
        dbManager.open();

        //button onclick
        addTodoBtn.setOnClickListener(this);
    }

    //button onclick value operations
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String username = usernameEditText.getText().toString();
                final String email = emailEditText.getText().toString();
                final String firstname = firstnameEditText.getText().toString();
                final String lastname = lastnameEditText.getText().toString();
                final String position = positionEditText.getText().toString();
                final String address = addressEditText.getText().toString();
                final String gender = genderEditText.getText().toString();
                final String phone = phoneEditText.getText().toString();
                final String salary = salaryEditText.getText().toString();
                final String password = passwordEditText.getText().toString();


                //   if (TextUtils.isEmpty(subjectEditText.getText()))
                //       subjectEditText.setError("required");
                //   else {}
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
//                if (email.isEmpty()) {
//                    emailEditText.setError("Email can't be Empty");
//                    emailEditText.requestFocus();
//                    return;
//                }

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
                if (position.isEmpty()) {
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

                if (!validateEmail()| !validatePassword()) {
                    dbManager.insert(username, email, firstname, lastname, position, address, gender, Integer.parseInt(phone), Double.parseDouble(salary), password);
                    return;
                }



                Intent main = new Intent(this, View_List_Activity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
    private boolean validateEmail() {
        String emailInput = emailEditText.getText().toString().trim();

        if (emailInput.isEmpty()) {
            emailEditText.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailEditText.setError("Please enter a valid email address");
            return false;
        } else {
            emailEditText.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = passwordEditText.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            passwordEditText.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordEditText.setError("Password too weak");
            return false;
        } else {
            passwordEditText.setError(null);
            return true;
        }
    }
}

