package com.one.demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.demo.R;
import com.one.demo.model.Result;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    List<Result> list;
    Context context;

    public LanguageAdapter(List<Result> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LanguageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.language_card, parent, false);
        return new LanguageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageAdapter.ViewHolder holder, int position) {
        Result result = list.get(position);
        Log.e("TAG@@@@", "onBindViewHolder: "+result.getName() );
        holder.multi_language.setText(result.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView multi_language;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            multi_language = itemView.findViewById(R.id.multi_language);
        }
    }
}
