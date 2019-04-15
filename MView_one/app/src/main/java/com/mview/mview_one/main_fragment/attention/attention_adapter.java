package com.mview.mview_one.main_fragment.attention;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.MainActivity;
import com.mview.mview_one.R;
import com.mview.mview_one.main_fragment.talk.TalkActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class attention_adapter extends RecyclerView.Adapter<attention_adapter.ViewHolder> {
    private ArrayList<attention_data> attention_data;
    private Context context;

    String modelNo;
    int position;

    public attention_adapter(Context context,ArrayList<attention_data> attention_data) {
        this.context = context;
        this.attention_data = attention_data;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public attention_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attention_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(attention_data.get(i).getShopImage())
                .resize(50, 50)
                .into(viewHolder.shopImage);
        viewHolder.shopLoc_1.setText(attention_data.get(i).getShopLoc_1());
        viewHolder.shopLoc_2.setText(attention_data.get(i).getShopLoc_2());
        viewHolder.shopName.setText(attention_data.get(i).getShopName());
        viewHolder.heartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.heartBtn.setImageResource(R.drawable.ic_heart);
                modelNo = attention_data.get(i).getModelNo();
                position = i;
                attention_data.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, attention_data.size());
                attentiondeleteData();
            }
        });
        viewHolder.chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.userType.equals("1")){
                    Toast.makeText(getContext(), "업체끼리는 채팅을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }else if(MainActivity.userType.equals("0")){
                    Intent intent = new Intent(getContext(), TalkActivity.class);
                    intent.putExtra("shopNo_chat",attention_data.get(i).getShopNo());
                    intent.putExtra("userNo_chat",MainActivity.userNo);
                    intent.putExtra("Name_chat",attention_data.get(i).getShopName());
                    context.startActivity(intent);
                }
            }
        });
    }

    public void attentiondeleteData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://alsrud55399.cafe24.com/attention_delete.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        AttentionFragment.notifyDataSetChanged(position);
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userNo", MainActivity.userNo);
                params.put("modelNo", modelNo);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return attention_data.size();
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView heartBtn;
        ImageView shopImage;
        TextView shopLoc_1;
        TextView shopLoc_2;
        TextView shopName;
        ImageView chatBtn;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ViewHolder(View view) {
            super(view);
            heartBtn = (ImageView)view.findViewById(R.id.heartBtn);
            shopImage = (ImageView)view.findViewById(R.id.shopImage);
            shopLoc_1 = (TextView)view.findViewById(R.id.shopLoc_1);
            shopLoc_2 = (TextView)view.findViewById(R.id.shopLoc_2);
            shopName = (TextView)view.findViewById(R.id.shopName);
            chatBtn = (ImageView)view.findViewById(R.id.chatBtn);
            GradientDrawable drawable=(GradientDrawable) context.getDrawable(R.drawable.image_rounding);
            shopImage.setBackground(drawable);
            shopImage.setClipToOutline(true);
        }
    }
}
