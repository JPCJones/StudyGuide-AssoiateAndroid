package com.example.studyguide_assoiateandroid.UserInterface.MaterialComponentSHRINE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.studyguide_assoiateandroid.R;
import com.example.studyguide_assoiateandroid.UserInterface.PagingEx.PagingWordDatabase;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.List;

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardRecyclerViewAdapter.ProductCardViewHolder> {
    private static final String TAG = "ProductCardRecyclerView";
    private List<Product> products;
    private Context context;

    public ProductCardRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setProducts(List<Product> products) {
        Log.d(TAG, "setProducts: " + products.toString());
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_item, parent, false);
       return new ProductCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
       if(position % 3 == 2)
       {
           float size = (float) 1.2;
           holder.cardView.setScaleX(size);
           holder.cardView.setScaleY(size);
           holder.cardView.setY(holder.cardView.getY() + 220);
       }
        Glide.with(context)
                .load(products.get(position).getImageURL())
                .error(R.drawable.ic_menu_gallery)
                .into(holder.imageView);
        holder.description.setText(products.get(position).getDescription() + " " + products.get(position).getPrice());
        holder.title.setText(products.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductCardViewHolder extends RecyclerView.ViewHolder{

        private MaterialCardView cardView;
        private TextView title, description;
        private ImageView imageView;
        public ProductCardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.parent);
            title = itemView.findViewById(R.id.product_title);
            description = itemView.findViewById(R.id.product_price);
            imageView = itemView.findViewById(R.id.product_image);
        }
    }
}
