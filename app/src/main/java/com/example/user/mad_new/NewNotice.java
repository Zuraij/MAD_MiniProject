package com.example.user.mad_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNotice extends AppCompatActivity {

    EditText topic_txt, description_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);

        topic_txt = findViewById(R.id.notice_topic);
        description_txt = findViewById(R.id.description_txt);
    }

    public void addNotice(View view) {

        String subject = topic_txt.getText().toString();
        String description = description_txt.getText().toString();

        if (subject.matches("")) {
            Toast.makeText(this, "Topic field Empty", Toast.LENGTH_SHORT).show();
        } else if (description.matches("")) {
            Toast.makeText(this, "Description field Empty", Toast.LENGTH_SHORT).show();
        } else {

            DbHelper dbHelper = new DbHelper(this);
            long rowID = dbHelper.addNotice(subject, description);

            if (rowID == -1) {
                Toast.makeText(this, "Notice Could not be Posted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notice Posted Successfully.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
