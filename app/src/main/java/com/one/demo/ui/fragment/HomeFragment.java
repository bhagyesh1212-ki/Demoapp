package com.one.demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.one.demo.R;
import com.one.demo.adapter.TitleAdapter;
import com.one.demo.model.ProductModel;
import com.one.demo.model.TitleModel;
import com.one.demo.model.UserModel;
import com.one.demo.ui.activity.LoginActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FirebaseAuth mAuth;
    private ImageView logout;
    private RecyclerView recycleview;
    private TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Edge-to-Edge Padding
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Views
        logout = view.findViewById(R.id.button);
        recycleview = view.findViewById(R.id.rcv);
        textView = view.findViewById(R.id.textView);

        // Fetch User Details
       fetchUserDetails();

        // Populate RecyclerView
        populateRecyclerView();

        // Logout Functionality
        logout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }

    private void fetchUserDetails() {
        String uid = mAuth.getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance()
                .getReference("UserDetail")
                .child(uid);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.child("name").getValue(String.class);
                textView.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to fetch user details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateRecyclerView() {
        ArrayList<ProductModel> productList = new ArrayList<>();
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));
        productList.add(new ProductModel(R.drawable.item, "Whole Farm Grocery Sugar", 20));

        ArrayList<TitleModel> titleList = new ArrayList<>();
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));
        titleList.add(new TitleModel("Groceries", productList));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(layoutManager);

        TitleAdapter adapter = new TitleAdapter(titleList, getContext());
        recycleview.setAdapter(adapter);
    }
}
