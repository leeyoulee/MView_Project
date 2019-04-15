package com.mview.mview_one.main_fragment.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mview.mview_one.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class model_adapter  extends RecyclerView.Adapter<model_adapter.ViewHolder> {
    private ArrayList<model_data> model_data;
    private Context context;

    public model_adapter(Context context,ArrayList<model_data> model_data) {
        this.context = context;
        this.model_data = model_data;
    }

    @Override
    public model_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_model_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Picasso.with(context).load(model_data.get(i).getModelImage_data())
                .resize(140, 120)
                .into(viewHolder.modelImage);
        viewHolder.modelName.setText(model_data.get(i).getModelName_data());
        viewHolder.modelLoc_1.setText(model_data.get(i).getModelLoc_1_data());
        viewHolder.modelLoc_2.setText(model_data.get(i).getModelLoc_2_data());
        if(model_data.get(i).getModelPrice().toString().equals("0")){
            viewHolder.modelPrice.setText("무료");
        }else{
            viewHolder.modelPrice.setText(model_data.get(i).getModelPrice()+"원");
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return model_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView modelImage;
        TextView modelName;
        TextView modelLoc_1;
        TextView modelLoc_2;
        TextView modelPrice;
        public ViewHolder(View view) {
            super(view);
            modelImage = (ImageView)view.findViewById(R.id.modelImage);
            modelName = (TextView)view.findViewById(R.id.modelName);
            modelLoc_1 = (TextView)view.findViewById(R.id.modelLoc_1);
            modelLoc_2 = (TextView)view.findViewById(R.id.modelLoc_2);
            modelPrice = (TextView)view.findViewById(R.id.modelPrice);
        }
    }
}
