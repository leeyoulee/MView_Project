package com.mview.mview_one.main_fragment.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mview.mview_one.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class shop_adapter  extends RecyclerView.Adapter<shop_adapter.ViewHolder> {
    private ArrayList<shop_data> shop_data;
    private Context context;

    public shop_adapter(Context context,ArrayList<shop_data> shop_data) {
        this.context = context;
        this.shop_data = shop_data;
    }

    @Override
    public shop_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_shop_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Picasso.with(context).load(shop_data.get(i).getShopImage_data())
                .resize(140, 120)
                .into(viewHolder.shopImage);
        viewHolder.shopName.setText(shop_data.get(i).getShopName_data());
        viewHolder.shopLoc_1.setText(shop_data.get(i).getShopLoc_1_data());
        viewHolder.shopLoc_2.setText(shop_data.get(i).getShopLoc_2_data());
    }

    @Override
    public int getItemCount() {
        return shop_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView shopImage;
        TextView shopName;
        TextView shopLoc_1;
        TextView shopLoc_2;
        public ViewHolder(View view) {
            super(view);
            shopImage = (ImageView)view.findViewById(R.id.shopImage);
            shopName = (TextView)view.findViewById(R.id.shopName);
            shopLoc_1 = (TextView)view.findViewById(R.id.shopLoc_1);
            shopLoc_2 = (TextView)view.findViewById(R.id.shopLoc_2);
        }
    }
}
