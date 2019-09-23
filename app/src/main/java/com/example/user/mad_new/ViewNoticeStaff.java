package com.example.user.mad_new;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewNoticeStaff extends AppCompatActivity implements deleteDialog.NoticeDialogListener{

    TextView topic, description;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice_staff);

        topic = findViewById(R.id.notice_topic);
        description = findViewById(R.id.notice_description);

        Intent intent = getIntent();
        topic.setText(intent.getStringExtra("topic_txt"));
        description.setText(intent.getStringExtra("description_txt"));
        id = intent.getStringExtra("id");
    }

    public void update(View view) {
        Intent intent = new Intent(this, UpdateNotice.class);
        intent.putExtra("topic_txt", topic.getText().toString());
        intent.putExtra("description_txt", description.getText().toString());
        intent.putExtra("id", id);
        startActivity(intent);
    }


    public void delete(View view) {

        deleteDialog dialog = new deleteDialog();
        dialog.show(getSupportFragmentManager(), "noticeDelete");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        DbHelper dbHelper = new DbHelper(this);
        dbHelper.deleteNotice(id);
        finish();

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

        dialog.dismiss();
    }
}
