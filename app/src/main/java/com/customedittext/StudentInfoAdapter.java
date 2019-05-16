package com.customedittext;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.ViewHolder> {
    private final List<StudentInfoModal> modalList;

    public StudentInfoAdapter(List<StudentInfoModal> modalList) {
        this.modalList = modalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_country_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        StudentInfoModal studentInfoModalList = modalList.get(i);
        viewHolder.tvUserName.setText(studentInfoModalList.getStudentName());
        viewHolder.tvUserAge.setText(studentInfoModalList.getStudentAge());
    }

    @Override
    public int getItemCount() {
        return modalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView tvUserName, tvUserAge;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_name);
            tvUserAge = itemView.findViewById(R.id.tv_age);

        }
    }
}
