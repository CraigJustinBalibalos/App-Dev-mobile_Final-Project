package com.example.finalprojectdjcgrocery.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalprojectdjcgrocery.R;
import com.example.finalprojectdjcgrocery.pojo.CartItem;
import com.example.finalprojectdjcgrocery.pojo.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartItemAdapter extends FirebaseRecyclerAdapter<CartItem,CartItemAdapter.CartViewHolder> {

    Context context;
    List<CartItem> itemList;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CartItemAdapter(@NonNull FirebaseRecyclerOptions<CartItem> options) {
        super(options);
    }

//    ArrayList<String> imgList;
//    ArrayList<String> nameList;
//    ArrayList<String> priceList;
//    ArrayList<String> qtyList;

//    public CartItemAdapter(Context context, ArrayList<String> imgList, ArrayList<String> nameList, ArrayList<String> priceList, ArrayList<String> qtyList) {
//        this.context = context;
//        this.imgList = imgList;
//        this.nameList = nameList;
//        this.priceList = priceList;
//        this.qtyList = qtyList;
//    }

//    @NonNull
//    @Override
//    public CartItemAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.row_child_cart, parent, false);
//        return new CartItemAdapter.CartViewHolder(v);
//    }

//    @Override
//    public void onBindViewHolder(@NonNull CartItemAdapter.CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.showName.setText(itemList.get(position).getpName());
//        holder.showPrice.setText(itemList.get(position).getPrice());
//        holder.showQty.setText(itemList.get(position).getQty());
//        Glide.with(context)
//                .asBitmap()
//                .load(itemList.get(position).getImgUrl())
//                .into(holder.showImg);
//
//        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseDatabase.getInstance().getReference().child("Cart").child("Username")
//                        .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
//            }
//        });
//    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_child_cart, parent, false);
        return new CartItemAdapter.CartViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull CartItem model) {
        holder.showName.setText(itemList.get(position).getpName());
        holder.showPrice.setText(itemList.get(position).getPrice());
        holder.showQty.setText(itemList.get(position).getQty());
        Glide.with(context)
                .asBitmap()
                .load(itemList.get(position).getImgUrl())
                .into(holder.showImg);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Cart").child("Username")
                        .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
            }
        });
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }



    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView showName, showPrice, showQty;
        Button deleteBtn;
        ImageView showImg;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            showName = (TextView) itemView.findViewById(R.id.cart_productName);
            showPrice = (TextView) itemView.findViewById(R.id.cart_productPrice);
            showQty = (TextView) itemView.findViewById(R.id.cart_productQuantity);
            showImg = (ImageView) itemView.findViewById(R.id.cart_productImg);
            deleteBtn = (Button) itemView.findViewById(R.id.deleteFromCartBtn);

        }
    }
}

