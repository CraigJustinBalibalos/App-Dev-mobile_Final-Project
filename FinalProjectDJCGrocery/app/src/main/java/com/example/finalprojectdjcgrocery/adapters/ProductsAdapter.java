package com.example.finalprojectdjcgrocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalprojectdjcgrocery.Login;
import com.example.finalprojectdjcgrocery.Products;
import com.example.finalprojectdjcgrocery.R;
import com.example.finalprojectdjcgrocery.interfaces.AddToCartInterface;
import com.example.finalprojectdjcgrocery.pojo.CartItem;
import com.example.finalprojectdjcgrocery.pojo.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{

    Context context;
    List<Product> productList;
    Products prod = new Products();

    public ProductsAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
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
                .load(productList.get(position).getImg())
                .into(holder.prodImg);

        holder.setAdd(new AddToCartInterface() {
            @Override
            public void addToCart(View view, int position) {
                addItem(productList.get(position));
            }
        });
    }

    private void addItem(Product product) {
        DatabaseReference userCart = FirebaseDatabase.getInstance().getReference("Cart").child(prod.uName);
        userCart.child(product.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    CartItem cartItem = snapshot.getValue(CartItem.class);
                    cartItem.setQty(cartItem.getQty()+1);
                    Map<String, Object> updateItem = new HashMap<>();
                    updateItem.put("qty", cartItem.getQty());
                    updateItem.put("total_price", cartItem.getQty()*Float.parseFloat(cartItem.getPrice()));

                    userCart.child(product.getKey()).updateChildren(updateItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    CartItem cartItem = new CartItem();
                    cartItem.setpName(product.getName());
                    cartItem.setImgUrl(product.getImg());
                    cartItem.setKey(product.getKey());
//                    cartItem.setUsername(prod.uName);
                    cartItem.setPrice(product.getPrice());
                    cartItem.setQty(1);
                    cartItem.setTotal_price(Float.parseFloat(product.getPrice()));

                    userCart.child(product.getKey()).setValue(cartItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView prodImg;
        Button addBtn, rmvBtn;

        AddToCartInterface add;

        public void setAdd(AddToCartInterface add) {
            this.add = add;
        }

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImg = (ImageView) itemView.findViewById(R.id.imageView4);

            addBtn = itemView.findViewById(R.id.button);
//            rmvBtn = itemView.findViewById(R.id.button2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            add.addToCart(view, getAdapterPosition());
        }
    }
}
