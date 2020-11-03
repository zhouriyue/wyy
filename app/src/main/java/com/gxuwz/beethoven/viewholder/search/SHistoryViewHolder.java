package com.gxuwz.beethoven.viewholder.search;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class SHistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView word;

    public SHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        word = itemView.findViewById(R.id.word);
    }
}
