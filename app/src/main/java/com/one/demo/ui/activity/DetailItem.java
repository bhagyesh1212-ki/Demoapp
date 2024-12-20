package com.one.demo.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.one.demo.R;
import com.one.demo.adapter.DeatilProductAdapter;
import com.one.demo.model.DeatilProductModel;

import java.util.ArrayList;

public class DetailItem extends AppCompatActivity {
    RecyclerView rcv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rcv2 = findViewById(R.id.rcv2);
        ArrayList<DeatilProductModel> list = new ArrayList<>();

        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));
        list.add(new DeatilProductModel(R.drawable.item, "Whole Farm Grocery Sugar", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.", 20));

        DeatilProductAdapter adapter = new DeatilProductAdapter(list, this);
        rcv2.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv2.setLayoutManager(layoutManager);
    }
}