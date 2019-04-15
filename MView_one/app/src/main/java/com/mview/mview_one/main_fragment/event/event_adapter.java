package com.mview.mview_one.main_fragment.event;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mview.mview_one.MainActivity;
import com.mview.mview_one.R;
import com.mview.mview_one.login.Main_LoginActivity;
import com.mview.mview_one.main_fragment.talk.TalkActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class event_adapter extends RecyclerView.Adapter<event_adapter.ViewHolder> {
    private ArrayList<event_data> event_data;
    private Context context;

    public event_adapter(Context context, ArrayList<event_data> event_data) {
        this.context = context;
        this.event_data = event_data;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public event_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(event_data.get(i).getShopImage())
                .resize(100, 100)
                .into(viewHolder.shopImage);
        viewHolder.shopLoc_1.setText(event_data.get(i).getShopLoc_1());
        viewHolder.shopLoc_2.setText(event_data.get(i).getShopLoc_2());
        viewHolder.shopName.setText(event_data.get(i).getShopName());
        viewHolder.eventTitle.setText(event_data.get(i).getEventTitle());
        viewHolder.modelPrice.setText(event_data.get(i).getModelPrice());
        viewHolder.rsvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.userNo.equals("userNo")) {
                    Toast.makeText(context, "로그인 후 이용해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (MainActivity.userType.equals("1")) {
                        Toast.makeText(context, "업체끼리는 채팅을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    } else if (MainActivity.userType.equals("0")) {
                        Intent intent = new Intent(context, TalkActivity.class);
                        intent.putExtra("shopNo_chat", event_data.get(i).getShopNo());
                        intent.putExtra("userNo_chat", MainActivity.userNo);
                        intent.putExtra("Name_chat", event_data.get(i).getShopName());
                        context.startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return event_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shopImage;
        TextView shopLoc_1;
        TextView shopLoc_2;
        TextView shopName;
        TextView eventTitle;
        TextView modelPrice;
        TextView rsvBtn;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ViewHolder(View view) {
            super(view);

            rsvBtn = view.findViewById(R.id.rsvBtn);
            shopImage = (ImageView) view.findViewById(R.id.shopImage);
            shopLoc_1 = (TextView) view.findViewById(R.id.shopLoc_1);
            shopLoc_2 = (TextView) view.findViewById(R.id.shopLoc_2);
            shopName = (TextView) view.findViewById(R.id.shopName);
            eventTitle = (TextView) view.findViewById(R.id.eventTitle);
            modelPrice = (TextView) view.findViewById(R.id.modelPrice);
            GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.image_rounding);
            shopImage.setBackground(drawable);
            shopImage.setClipToOutline(true);
        }
    }
}