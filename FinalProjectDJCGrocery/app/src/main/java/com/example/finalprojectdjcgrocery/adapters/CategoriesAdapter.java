package com.example.finalprojectdjcgrocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.finalprojectdjcgrocery.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>{

    Context context;
    ArrayList<String> catList;
    ArrayList<String> imgList;

    public CategoriesAdapter(Context context, ArrayList<String> catList, ArrayList<String> imgList) {
        this.context = context;
        this.catList = catList;
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_child_categories, parent, false);
        return new CategoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        String category = catList.get(position);
        holder.catName.setText(category);
        Glide.with(context)
                .asBitmap()
                .load(imgList.get(position))
                .into(holder.catImg);
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView catName;
        ImageView catImg;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = (TextView) itemView.findViewById(R.id.categoryName);
            catImg = (ImageView) itemView.findViewById(R.id.categoryImg);
        }
    }
}
