package com.one.demo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.one.demo.model.MultipleResources;
import com.one.demo.model.VegetablesAndFruit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ImageView logout;
    ImageButton seeall;
    RecyclerView recycleview;
    APIInterface apiInterface;
    private List<MultipleResources> dataList = new ArrayList<>();
    TextView textView;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.button);
        recycleview = findViewById(R.id.rcv);
        textView = findViewById(R.id.textView);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = database.getReference("UserDetail").child(uid);


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.child("name").getValue(String.class);
                textView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to fetch", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<ProductModel> list = new ArrayList<>();
        list.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Suga", 20));
        list.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Suga", 20));
        list.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Suga", 20));
        list.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Suga", 20));
        list.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Suga", 20));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(layoutManager);
//        LinearLayout linearLayout = new LinearLayout(this);
//        recycleview.setLayoutManager(linearLayout);
        fetchResources();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void fetchResources() {
        Call<MultipleResources> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResources>() {
            @Override
            public void onResponse(Call<MultipleResources> call, Response<MultipleResources> response) {
                List<VegetablesAndFruit> items = response.body().getData().getVegetablesAndFruits();
                Log.d("TAG@@@", response.code() + "");
                ArrayList<TitleModel> title = new ArrayList<>();
                for (int i = 0; i < response.body().getData().getVegetablesAndFruits().size(); i++) {
                    title.add(new TitleModel("Groceries", response.body().getData().getVegetablesAndFruits()));
//                    Log.i("TAG@@@", "onResponse: "+response.body().getData().getVegetablesAndFruits().get(i).getCategoryName());
//                    Log.i("TAG@@@", "onResponse: "+response.body().getData().getVegetablesAndFruits().get(i).getCategoryImage());
                }
//                TitleAdapter adapter = new TitleAdapter(title, this);
//                recycleview.setAdapter(adapter);

                TitleAdapter adapter = new TitleAdapter(title, MainActivity.this);
                recycleview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MultipleResources> call, Throwable t) {
                call.cancel();
            }
        });
    }
}

