package com.example.finalprojectdjcgrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{

    Context context;
    ArrayList<String> prodImgList;

    public ProductsAdapter(Context context, ArrayList<String> prodImgList) {
        this.context = context;
        this.prodImgList = prodImgList;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_child_products, parent, false);
        return new ProductsAdapter.ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(prodImgList.get(position))
                .into(holder.prodImg);
    }

    @Override
    public int getItemCount() {
        return prodImgList.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{

        ImageView prodImg;
        Button addBtn, rmvBtn;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImg = (ImageView) itemView.findViewById(R.id.imageView4);

            addBtn = itemView.findViewById(R.id.button);
            rmvBtn = itemView.findViewById(R.id.button2);

        }
    }
}
