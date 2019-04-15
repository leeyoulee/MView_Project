package com.mview.mview_one.main_fragment.review;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mview.mview_one.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class lately_adapter extends RecyclerView.Adapter<lately_adapter.ViewHolder> {
    private ArrayList<lately_data> lately_data;
    private Context context;

    public lately_adapter(Context context, ArrayList<lately_data> lately_data) {
        this.context = context;
        this.lately_data = lately_data;
    }

    @Override
    public lately_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(lately_data.get(i).getModelImage())
                .resize(80, 80)
                .into(viewHolder.modelImage);
        viewHolder.rsvDate.setText(lately_data.get(i).getRsvDate());
        viewHolder.category.setText(lately_data.get(i).getCategory());
        viewHolder.pay.setText(lately_data.get(i).getPay());
        viewHolder.shopName.setText(lately_data.get(i).getShopName());
        viewHolder.reviewGrade.setText(lately_data.get(i).getReviewGrade());
        viewHolder.reviewContents.setText(lately_data.get(i).getReviewContents());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewContentsActivity.class);
                intent.putExtra("reviewNo",lately_data.get(i).getReviewNo());
                intent.putExtra("reviewViews",lately_data.get(i).getReviewViews());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lately_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView modelImage;
        TextView rsvDate;
        TextView category;
        TextView pay;
        TextView shopName;
        TextView reviewGrade;
        TextView reviewContents;

        public ViewHolder(View view) {
            super(view);
            modelImage = (ImageView) view.findViewById(R.id.modelImage);
            rsvDate = (TextView) view.findViewById(R.id.rsvDate);
            category = (TextView) view.findViewById(R.id.category);
            pay = (TextView) view.findViewById(R.id.pay);
            shopName = (TextView) view.findViewById(R.id.shopName);
            reviewGrade = (TextView) view.findViewById(R.id.reviewGrade);
            reviewContents = (TextView) view.findViewById(R.id.reviewContents);
        }
    }
}
