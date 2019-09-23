package com.example.user.mad_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewNoticeStudent extends AppCompatActivity {

    TextView topic, description;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice_student);

        topic = findViewById(R.id.notice_topic);
        description = findViewById(R.id.notice_description);

        Intent intent = getIntent();
        topic.setText(intent.getStringExtra("topic_txt"));
        description.setText(intent.getStringExtra("description_txt"));
        id = intent.getStringExtra("id");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
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
