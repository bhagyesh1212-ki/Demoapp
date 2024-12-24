package com.one.demo.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.one.demo.APIClient;
import com.one.demo.LanguageApi;
import com.one.demo.adapter.LnaguageAdapter;
import com.one.demo.databinding.FragmentLanguageBinding;
import com.one.demo.modelapi.Example;
import com.one.demo.modelapi.Result;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LanguageFragment extends Fragment {

    FragmentLanguageBinding binding;
    LanguageApi apiInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLanguageBinding.inflate(inflater, container, false);
        apiInterface = APIClient.getClient().create(LanguageApi.class);
        progressbar(true);
        Call<Example> call = apiInterface.getLanguages("Bearer 30|Ie5jcTwaQUgrciynIcNSyyLIEEcAUU5h1zMnyysu23a343d1");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                progressbar(false);
                Example resource = response.body();
                List<Result> results = resource.getResult();
                LnaguageAdapter adapter = new LnaguageAdapter(results, getContext());
                binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
                binding.recyclerview.setAdapter(adapter);

//                for (Result result :
//                        resource.getResult()) {
//                    Log.d("TAG@@@", result.getName());
//                    Log.d("TAG@@@", result.getCreatedAt());
//                    Log.d("TAG@@@", result.getStatus());
//                    Log.d("TAG@@@", result.getImage());
//                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("TAG@@@", "onFailure: " + t);
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    private  void progressbar(boolean show){
        if(show){
            binding.progressbar.setVisibility(View.VISIBLE);
        }else{
            binding.progressbar.setVisibility(View.GONE);
        }
    }
}