package com.one.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeatilProductAdapter extends RecyclerView.Adapter<DeatilProductAdapter.ViewHolder> {

    ArrayList<DeatilProductModel> list;
    Context context;

    public DeatilProductAdapter(ArrayList<DeatilProductModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.demo_subproduct, parent, false);
        return new DeatilProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeatilProductModel model = list.get(position);
        holder.product.setImageResource(model.getProduct());
        holder.product_title.setText(model.getProduct_title());
        holder.product_desc.setText(model.getProduct_desc());
        holder.product_price.setText(""+model.getProduct_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product;
        TextView product_title;
        TextView product_desc;
        TextView product_price;

        public ViewHolder(View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.product);
            product_price = itemView.findViewById(R.id.product_price);
            product_desc = itemView.findViewById(R.id.product_desc);
            product_title = itemView.findViewById(R.id.product_title);
        }
    }
}
