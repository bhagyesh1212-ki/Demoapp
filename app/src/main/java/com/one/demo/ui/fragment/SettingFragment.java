package com.one.demo.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.one.demo.databinding.FragmentSettingBinding;


public class SettingFragment extends Fragment {

    FragmentSettingBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    private static final int STORAGE_PERMISSION_CODE = 101;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //    binding.circleImageView.setImageURI(Uri.parse("content://media/external/images/media/1000109756"));
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        String uid = auth.getCurrentUser().getUid();
        DatabaseReference reference = database.getReference("UserDetail").child(uid);

        if (!hasStoragePermission()) {
            requestStoragePermission();
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String valuename = snapshot.child("name").getValue(String.class);
                String valueemail = snapshot.child("email").getValue(String.class);
                binding.name.setText(valuename);
                binding.email.setText(valueemail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
        binding.profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasStoragePermission()) {
                    requestStoragePermission();
                } else {
                    // Toast.makeText(getContext(), "Permissions already granted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.setType("image/*");
                    startActivityForResult(i, 2);
                }
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedata();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data.getData() != null) {
            Uri uri = data.getData();
            binding.circleImageView.setImageURI(uri);
            String uid = auth.getCurrentUser().getUid();
        //    Log.d("LOG@@@", uri.toString());
            //database.getReference("UserDetail").child(uid).child("ProfileImage").setValue(uri.toString());
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getContext(), "Image selection canceled", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Failed to select image", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatedata() {
        String name = binding.name.getText().toString();
        database.getReference("UserDetail").child(auth.getUid()).child("name").setValue(name);
        Toast.makeText(getContext(), "name is updated", Toast.LENGTH_SHORT).show();
    }

    private boolean hasStoragePermission() {
        return ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(getContext(), "Storage permissions are needed to access files.", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Storage permissions granted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Storage permissions denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}