package com.mview.mview_one.main_fragment.talk;

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

import com.mview.mview_one.MainActivity;
import com.mview.mview_one.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class chatlist_adapter extends RecyclerView.Adapter<chatlist_adapter.ViewHolder> {
    private ArrayList<chatlist_data> chatlist_data;
    private Context context;
    public static String shopNo_chat;
    public static String userNo_chat;
    public static String Name_chat;

    public chatlist_adapter(Context context, ArrayList<chatlist_data> chatlist_data) {
        this.context = context;
        this.chatlist_data = chatlist_data;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public chatlist_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chatlist_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(chatlist_data.get(i).getShopImage())
                .resize(50, 50)
                .into(viewHolder.shopImage);
        viewHolder.shopName.setText(chatlist_data.get(i).getShopName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.userType.equals("0")){
                    shopNo_chat = chatlist_data.get(i).getShopNo();
                    userNo_chat = MainActivity.userNo;
                    Name_chat = chatlist_data.get(i).getShopName();
                    Intent intent = new Intent(getContext(), TalkActivity.class);
                    intent.putExtra("shopNo_chat",shopNo_chat);
                    intent.putExtra("userNo_chat",userNo_chat);
                    intent.putExtra("Name_chat",Name_chat);
                    context.startActivity(intent);
                }else {
                    shopNo_chat = MainActivity.shopNo_result;
                    userNo_chat = chatlist_data.get(i).getShopNo();
                    Name_chat = chatlist_data.get(i).getShopName();
                    Intent intent = new Intent(getContext(), TalkActivity.class);
                    intent.putExtra("shopNo_chat",shopNo_chat);
                    intent.putExtra("userNo_chat",userNo_chat);
                    intent.putExtra("Name_chat",Name_chat);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatlist_data.size();
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView shopImage;
        TextView shopName;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ViewHolder(View view) {
            super(view);
            shopImage = (ImageView)view.findViewById(R.id.shopImage);
            shopName = (TextView)view.findViewById(R.id.shopName);
            GradientDrawable drawable=(GradientDrawable) context.getDrawable(R.drawable.image_rounding);
            shopImage.setBackground(drawable);
            shopImage.setClipToOutline(true);
        }
    }

}