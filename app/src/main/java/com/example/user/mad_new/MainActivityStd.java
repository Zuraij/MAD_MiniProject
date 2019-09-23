package com.example.user.mad_new;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityStd extends AppCompatActivity {
    DatabaseHelperStd mydb;
    Button InsertStudent ,ViewStudents ,UpdateStudents ,DeleteStudents;
    EditText ssFirstName , ssLastName , ssAge , ssAddress , ssEmailID ,ssContactNumber , ssUserName , ssPassword ,ssID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainstd);
        mydb = new DatabaseHelperStd(this);

        ssFirstName = (EditText)findViewById(R.id.ssFirstName);
        ssLastName = (EditText)findViewById(R.id.ssLastName);
        ssAge = (EditText)findViewById(R.id.ssAge);
        ssAddress = (EditText)findViewById(R.id.ssAddress);
        ssContactNumber = (EditText)findViewById(R.id.ssContactNumber);
        ssEmailID = (EditText)findViewById(R.id.ssEmailID);
        ssUserName = (EditText)findViewById(R.id.ssUsername);
        ssPassword = (EditText)findViewById(R.id.ssPassword);
        ssID = (EditText)findViewById(R.id.ssID);

        InsertStudent = (Button)findViewById(R.id.InsertStudent);
        ViewStudents = (Button)findViewById(R.id.ViewStudents);
        UpdateStudents = (Button)findViewById(R.id.UpdateStudents);
        DeleteStudents = (Button)findViewById(R.id.DeleteStudents);


        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }
    public void DeleteData(){
        DeleteStudents.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = mydb.deleteData(ssID.getText().toString());
                        if(deletedRows > 0){

                            Toast toast = Toast.makeText(MainActivityStd.this, "Data Deleted", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(MainActivityStd.this,"Data Not Deleted", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }


        );


    }


    public void UpdateData(){
        UpdateStudents.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = mydb.updateData(ssID.getText().toString(),ssFirstName.getText().toString(),
                                ssLastName.getText().toString(),
                                ssAge.getText().toString(),
                                ssAddress.getText().toString(),
                                ssContactNumber.getText().toString(),
                                ssEmailID.getText().toString(),
                                ssUserName.getText().toString(),
                                ssPassword.getText().toString());

                        if(isUpdated == true){

                            Toast toast = Toast.makeText(MainActivityStd.this, "Data Updated", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(MainActivityStd.this,"Data Not Updated", Toast.LENGTH_LONG);
                            toast.show();
                        }






                    }
                }


        );


    }



    public void AddData(){
        InsertStudent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(ssFirstName.getText().toString(),
                                ssLastName.getText().toString(),
                                ssAge.getText().toString(),
                                ssAddress.getText().toString(),
                                ssContactNumber.getText().toString(),
                                ssEmailID.getText().toString(),
                                ssUserName.getText().toString(),
                                ssPassword.getText().toString()

                        );
                        if (ssUserName.getText().toString().isEmpty()) {
                            ssUserName.setError("Username can't be Empty");
                            ssUserName.requestFocus();
                            return;
                        }
                        if (ssPassword.getText().toString().isEmpty()) {
                            ssPassword.setError("Password can't be Empty");
                            ssPassword.requestFocus();
                            return;
                        }
                        if (ssFirstName.getText().toString().isEmpty()) {
                            ssFirstName.setError("First name can't be Empty");
                            ssFirstName.requestFocus();
                            return;
                        }
                        if (ssLastName.getText().toString().isEmpty()) {
                            ssLastName.setError("Last name can't be Empty");
                            ssLastName.requestFocus();
                            return;
                        }
                        if (ssAge.getText().toString().isEmpty()) {
                            ssAge.setError("Age can't be Empty");
                            ssAge.requestFocus();
                            return;
                        }
                        if (ssEmailID.getText().toString().isEmpty()) {
                            ssEmailID.setError("Email can't be Empty");
                            ssEmailID.requestFocus();
                            return;
                        }

                        if (ssAddress.getText().toString().isEmpty()) {
                            ssAddress.setError("Address can't be Empty");
                            ssAddress.requestFocus();
                            return;
                        }
                        if (ssContactNumber.getText().toString().isEmpty()) {
                            ssContactNumber.setError(" CONTACT can't be Empty");
                            ssContactNumber.requestFocus();
                            return;
                        }


                        if(isInserted == true) {
                            Toast toast = Toast.makeText(MainActivityStd.this, "Data Inserted", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(MainActivityStd.this,"Data Not Inserted", Toast.LENGTH_LONG);
                            toast.show();
                        }



                    }
                }
        );

    }
    public  void viewAll(){
        ViewStudents.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error","No data Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("FirstName:"+res.getString(1)+"\n");
                            buffer.append("LastName :"+res.getString(2)+"\n");
                            buffer.append("Age :"+res.getString(3)+"\n");
                            buffer.append("Email :"+res.getString(4)+"\n");
                            buffer.append("ContactNo :"+res.getString(5)+"\n");
                            buffer.append("Address :"+res.getString(6)+"\n");
                            buffer.append("Username :"+res.getString(7)+"\n");
                            buffer.append("Password:"+res.getString(8)+"\n\n");


                        }
                        //show all data
                        showMessage("Data",buffer.toString());

                    }
                }

        );

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }




}
