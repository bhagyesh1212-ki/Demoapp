package com.one.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.demo.R;
import com.one.demo.modelapi.Example;
import com.one.demo.modelapi.Result;

import java.util.ArrayList;
import java.util.List;

public class LnaguageAdapter extends RecyclerView.Adapter<LnaguageAdapter.ViewHolder> {
    List<Result> model;
    Context context;

    public LnaguageAdapter(List<Result> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.language_card, parent, false);
        return new LnaguageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result modell = model.get(position);
        holder.multi_language.setText(modell.getName());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView multi_language;
        public ViewHolder(View itemView) {
            super(itemView);
            multi_language = itemView.findViewById(R.id.multi_language);
        }
    }
}
