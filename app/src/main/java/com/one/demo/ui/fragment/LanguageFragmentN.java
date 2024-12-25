package com.one.demo.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.one.demo.APIClient;
import com.one.demo.APIInterface;
import com.one.demo.adapter.LanguageAdapter;
import com.one.demo.databinding.FragmentLanguageNBinding;
import com.one.demo.model.Example;
import com.one.demo.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanguageFragmentN extends Fragment {

    APIInterface apiInterface;
    FragmentLanguageNBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLanguageNBinding.inflate(inflater, container, false);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Example> call = apiInterface.getdata("Bearer 30|Ie5jcTwaQUgrciynIcNSyyLIEEcAUU5h1zMnyysu23a343d1");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example resource = response.body();
                List<Result> result = resource.getResult();
                LanguageAdapter adapter = new LanguageAdapter(result,getContext());
                binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext() ,2));
                binding.recyclerview.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t){
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}