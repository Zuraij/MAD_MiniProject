package com.example.user.mad_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NoticeStaff extends AppCompatActivity {

    Map<String, ArrayList<String>> map = new HashMap<>();
    ArrayList<String> topics = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_staff);

        DbHelper dbHelper = new DbHelper(this);

        map = dbHelper.initMessages();
        topics = map.get("topics");
        descriptions = map.get("descriptions");
        id = map.get("id");

        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        DbHelper dbHelper = new DbHelper(this);

        map = dbHelper.initMessages();
        topics = map.get("topics");
        descriptions = map.get("descriptions");
        id = map.get("id");

        initRecyclerView();
    }

    private void initRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(topics, descriptions, id,"staff", this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void newNotice(View view) {

        startActivity(new Intent(this, NewNotice.class));
    }
}
