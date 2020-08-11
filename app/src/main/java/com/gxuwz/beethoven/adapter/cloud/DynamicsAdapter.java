package com.gxuwz.beethoven.adapter.cloud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.LocalMusicAdapter;
import com.gxuwz.beethoven.model.entity.dynamics.Dynamics;

import java.util.List;

public class DynamicsAdapter extends RecyclerView.Adapter<DynamicsAdapter.DynamicsViewHolder> {

    Context context;
    List<Dynamics> dynamicsList;

    public DynamicsAdapter(Context context, List<Dynamics> dynamicsList) {
        this.context = context;
        this.dynamicsList = dynamicsList;
    }

    @NonNull
    @Override
    public DynamicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DynamicsViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_cloud_dynamics_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dynamicsList.size();
    }

    class DynamicsViewHolder extends RecyclerView.ViewHolder{

        public DynamicsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
