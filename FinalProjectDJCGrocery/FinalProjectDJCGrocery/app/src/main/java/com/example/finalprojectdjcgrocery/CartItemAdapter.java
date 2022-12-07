package com.example.finalprojectdjcgrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartViewHolder>{

    Context context;
    ArrayList<String> imgList;
    ArrayList<String> nameList;
    ArrayList<String> priceList;
    ArrayList<String> qtyList;

    public CartItemAdapter(Context context, ArrayList<String> imgList, ArrayList<String> nameList, ArrayList<String> priceList, ArrayList<String> qtyList) {
        this.context = context;
        this.imgList = imgList;
        this.nameList = nameList;
        this.priceList = priceList;
        this.qtyList = qtyList;
    }

    @NonNull
    @Override
    public CartItemAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_child_cart, parent, false);
        return new CartItemAdapter.CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.CartViewHolder holder, int position) {
        holder.showName.setText(nameList.get(position));
        holder.showPrice.setText(priceList.get(position));
        holder.showQty.setText(qtyList.get(position));
        Glide.with(context)
                .asBitmap()
                .load(imgList.get(position))
                .into(holder.showImg);
    }


    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView showName, showPrice, showQty;
        ImageView showImg;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            showName = (TextView) itemView.findViewById(R.id.cart_productName);
            showPrice = (TextView) itemView.findViewById(R.id.cart_productPrice);
            showQty = (TextView) itemView.findViewById(R.id.cart_productQuantity);
            showImg = (ImageView) itemView.findViewById(R.id.cart_productImg);

        }
    }
}

