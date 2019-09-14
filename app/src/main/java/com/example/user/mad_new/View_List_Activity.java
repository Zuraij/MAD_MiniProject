package com.example.user.mad_new;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class View_List_Activity extends AppCompatActivity {
    final String[] Values_from = new String[] {DatabaseHelper._ID, DatabaseHelper.USERNAME,DatabaseHelper.EMAIL,
            DatabaseHelper.FIRSTNAME,DatabaseHelper.LASTNAME,DatabaseHelper.POSITION,DatabaseHelper.ADDRESS,DatabaseHelper.GENDER,DatabaseHelper.PHONE
            ,DatabaseHelper.SALARY
            , DatabaseHelper.PASSWORD };

    final int[] Values_to = new int[] { R.id.id, R.id.usernameL, R.id.email, R.id.firstname, R.id.lastname, R.id.position, R.id.address, R.id.gender, R.id.phone, R.id.salary, R.id.password };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_view);


        //open database and fetch the data
        DBManager dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();


        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_view__list_, cursor, Values_from, Values_to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView userTextView = (TextView) view.findViewById(R.id.usernameL);
                TextView emailTextView = (TextView) view.findViewById(R.id.email);
                TextView firstTextView = (TextView) view.findViewById(R.id.firstname);
                TextView lastTextView = (TextView) view.findViewById(R.id.lastname);
                TextView postionTextView = (TextView) view.findViewById(R.id.position);
                TextView addressTextView = (TextView) view.findViewById(R.id.address);
                TextView genderTextView = (TextView) view.findViewById(R.id.gender);
                TextView phoneTextView = (TextView) view.findViewById(R.id.phone);
                TextView salaryTextView = (TextView) view.findViewById(R.id.salary);
                TextView passTextView = (TextView) view.findViewById(R.id.password);

                String id = idTextView.getText().toString();
                String username = userTextView.getText().toString();
                String email = emailTextView.getText().toString();
                String firstname = firstTextView.getText().toString();
                String lastname = lastTextView.getText().toString();
                String poss= postionTextView.getText().toString();
                String address = addressTextView.getText().toString();
                String gender = genderTextView.getText().toString();
                String phone = phoneTextView.getText().toString();
                String salary = salaryTextView.getText().toString();
                String password = passTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), Modify_Staff_Activity.class);

                modify_intent.putExtra("pass", password);
                modify_intent.putExtra("salary", salary);
                modify_intent.putExtra("phone", phone);
                modify_intent.putExtra("gender", gender);
                modify_intent.putExtra("address", address);
                modify_intent.putExtra("position", position);
                modify_intent.putExtra("last", lastname);
                modify_intent.putExtra("first", firstname);
                modify_intent.putExtra("email", email);
                modify_intent.putExtra("user", username);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.add_record) {
//
//            Intent add_mem = new Intent(this, ADD_staff_activity.class);
//            startActivity(add_mem);
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


    public void gotoaddstaffpage(View view) {
        Intent add_mem = new Intent(this, ADD_staff_activity.class);
        startActivity(add_mem);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menulogout:
                startActivity(new Intent(this,Staff_Login.class));
        }
        return  true;
    }
}
