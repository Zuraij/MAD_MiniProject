package com.example.user.mad_new;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> topics = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    private Context context;
    private String type;

    public RecyclerViewAdapter(ArrayList<String> topics, ArrayList<String> descriptions, ArrayList<String> id, String type, Context context) {
        this.id = id;
        this.topics = topics;
        this.descriptions = descriptions;
        this.type = type;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent , false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.topic.setText(topics.get(position));
        holder.description.setText(descriptions.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type.matches("staff")) {
                    Intent intent = new Intent(context,  ViewNoticeStaff.class );
                    intent.putExtra("topic_txt", topics.get(position));
                    intent.putExtra("description_txt", descriptions.get(position));
                    intent.putExtra("id", id.get(position));
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context,  ViewNoticeStudent.class );
                    intent.putExtra("topic_txt", topics.get(position));
                    intent.putExtra("description_txt", descriptions.get(position));
                    intent.putExtra("id", id.get(position));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView topic;
        TextView description;
        ConstraintLayout parentLayout;

        public ViewHolder( View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.message_subject);
            description = itemView.findViewById(R.id.message_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
