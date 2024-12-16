package com.one.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.one.demo.model.VegetablesAndFruit;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<VegetablesAndFruit> list;

    Context context;

    public ProductAdapter(List<VegetablesAndFruit> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.demo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VegetablesAndFruit model = list.get(position);
//        holder.product.setImageResource(model.getProduct());
        holder.product_name.setText(model.getCategoryName());
        Glide.with(holder.product.getContext())
                .load(model.getCategoryImage()) // URL or resource ID
                .placeholder(R.drawable.item) // Placeholder image while loading
                .error(R.drawable.splash_icon) // Error image if load fails
                .into(holder.product);
//        holder.product_price.setText( ""+model.getProduct_price());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product;
        TextView product_name;
        TextView product_price;

        public ViewHolder(View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.product);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);

        }
    }
}
