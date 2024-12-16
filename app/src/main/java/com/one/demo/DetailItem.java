package com.one.demo;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailItem extends AppCompatActivity {
    RecyclerView rcv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Groceries");
        }
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


        DeatilProductAdapter adapter = new DeatilProductAdapter(list , this);
        rcv2.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv2.setLayoutManager(layoutManager);
    }
}