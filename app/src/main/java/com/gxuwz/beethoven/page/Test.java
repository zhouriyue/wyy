package com.gxuwz.beethoven.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.gxuwz.beethoven.R;

public class Test extends AppCompatActivity {

    LayoutInflater inflater;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
