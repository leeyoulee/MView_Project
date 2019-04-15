package com.mview.mview_one.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mview.mview_one.R;

import java.util.ArrayList;

public class IdSearch_adapter extends RecyclerView.Adapter<IdSearch_adapter.ViewHolder>{
    private ArrayList<IdSearch_data> IdSearch_data;
    private Context context;

    public IdSearch_adapter(Context context, ArrayList<IdSearch_data> IdSearch_data) {
        this.context = context;
        this.IdSearch_data = IdSearch_data;
    }

    @Override
    public IdSearch_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.idsearch_item, viewGroup, false);
        return new IdSearch_adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final IdSearch_adapter.ViewHolder viewHolder, final int i) {
        StringBuilder builder = new StringBuilder(IdSearch_data.get(i).getIdResult());

        if(IdSearch_data.get(i).getIdResult().toString().equals("null")){
            viewHolder.userId.setText("해당하는 아이디가 없습니다.");
        }else{
            for(int j = 2; j < IdSearch_data.get(i).getIdResult().length() -2; j++){
                builder.setCharAt(j, '*');
            }
            viewHolder.userId.setText(builder.toString());
        }
    }

    @Override
    public int getItemCount() {
        return IdSearch_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView userId;

        public ViewHolder(View view) {
            super(view);
            userId = (TextView)view.findViewById(R.id.userId);
        }
    }
}

class IdSearch_data {
    private String IdResult;

    public IdSearch_data(String idResult) {
        IdResult = idResult;
    }

    public String getIdResult() {
        return IdResult;
    }

    public void setIdResult(String idResult) {
        IdResult = idResult;
    }
}