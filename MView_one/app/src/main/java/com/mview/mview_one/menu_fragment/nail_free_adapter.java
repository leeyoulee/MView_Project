package com.mview.mview_one.menu_fragment;

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

public class nail_free_adapter extends RecyclerView.Adapter<nail_free_adapter.ViewHolder> {

    private ArrayList<nail_free_data> nail_free_data;
    private Context context;

    public nail_free_adapter(Context context, ArrayList<nail_free_data> nail_free_data) {
        this.context = context;
        this.nail_free_data = nail_free_data;
    }

    @Override
    public nail_free_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_item_free, viewGroup, false);
        return new nail_free_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(nail_free_adapter.ViewHolder viewHolder, int i) {
        Picasso.with(context).load(nail_free_data.get(i).getModelImage())
                .resize(140, 120)
                .into(viewHolder.modelImage);
        viewHolder.modelName.setText(nail_free_data.get(i).getModelName());
        viewHolder.modelLoc_1.setText(nail_free_data.get(i).getModelLoc_1());
        viewHolder.modelLoc_2.setText(nail_free_data.get(i).getModelLoc_2());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return nail_free_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView modelImage;
        TextView modelName;
        TextView modelLoc_1;
        TextView modelLoc_2;
        public ViewHolder(View view) {
            super(view);
            modelImage = (ImageView)view.findViewById(R.id.modelImage);
            modelName = (TextView)view.findViewById(R.id.modelName);
            modelLoc_1 = (TextView)view.findViewById(R.id.modelLoc_1);
            modelLoc_2 = (TextView)view.findViewById(R.id.modelLoc_2);
        }
    }

    public static class nail_free_data {

        private String modelNo;
        private String modelImage;
        private String modelName;
        private String modelLoc_1;
        private String modelLoc_2;
        private String modelPrice;

        public nail_free_data(String modelNo, String modelImage, String modelName, String modelLoc_1, String modelLoc_2, String modelPrice) {
            this.modelNo = modelNo;
            this.modelImage = modelImage;
            this.modelName = modelName;
            this.modelLoc_1 = modelLoc_1;
            this.modelLoc_2 = modelLoc_2;
            this.modelPrice = modelPrice;
        }

        public String getModelNo() {
            return modelNo;
        }

        public void setModelNo(String modelNo) {
            this.modelNo = modelNo;
        }

        public String getModelImage() {
            return modelImage;
        }

        public void setModelImage(String modelImage) {
            this.modelImage = modelImage;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getModelLoc_1() {
            return modelLoc_1;
        }

        public void setModelLoc_1(String modelLoc_1) {
            this.modelLoc_1 = modelLoc_1;
        }

        public String getModelLoc_2() {
            return modelLoc_2;
        }

        public void setModelLoc_2(String modelLoc_2) {
            this.modelLoc_2 = modelLoc_2;
        }

        public String getModelPrice() {
            return modelPrice;
        }

        public void setModelPrice(String modelPrice) {
            this.modelPrice = modelPrice;
        }
    }
}

