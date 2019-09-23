package com.example.user.mad_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNotice extends AppCompatActivity {

    EditText topic_txt, description_txt;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notice);

        topic_txt = findViewById(R.id.update_topic);
        description_txt = findViewById(R.id.update_description);

        Intent intent = getIntent();
        topic_txt.setText(intent.getStringExtra("topic_txt"));
        description_txt.setText(intent.getStringExtra("description_txt"));
        id = intent.getStringExtra("id");

    }

    public void updateNotice(View view) {

        Intent intent = getIntent();

        String topic = topic_txt.getText().toString();
        String description = description_txt.getText().toString();
        String notice_id = intent.getStringExtra("id");

        if (topic.matches("")) {
            Toast.makeText(this, "Topic field Empty", Toast.LENGTH_SHORT).show();
        } else if (description.matches("")) {
            Toast.makeText(this, "Description field Empty", Toast.LENGTH_SHORT).show();
        } else {

            DbHelper dbHelper = new DbHelper(this);
            System.out.println(id);
            long rowID = dbHelper.updateNotice(notice_id, topic, description);

            if (rowID == -1) {
                Toast.makeText(this, "Notice Could not be Posted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notice Posted Successfully.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
