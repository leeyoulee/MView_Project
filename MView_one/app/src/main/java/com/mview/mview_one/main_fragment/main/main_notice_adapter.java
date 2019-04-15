package com.mview.mview_one.main_fragment.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mview.mview_one.notice_qna.NoticeContentsActivity;
import com.mview.mview_one.R;

import java.util.ArrayList;

public class main_notice_adapter extends RecyclerView.Adapter<main_notice_adapter.ViewHolder>{
    private ArrayList<main_notice_data> main_notice_data;
    private Context context;

    public main_notice_adapter(Context context, ArrayList<main_notice_data> main_notice_data) {
        this.context = context;
        this.main_notice_data = main_notice_data;
    }

    @Override
    public main_notice_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_notice_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.noticeTitle.setText(main_notice_data.get(i).getNoticeTitle_data());
        viewHolder.noticeDate.setText(main_notice_data.get(i).getNoticeDate_data());
        viewHolder.mainNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoticeContentsActivity.class);
                intent.putExtra("noticeNo",main_notice_data.get(i).getNoticeNo_data());
                context.startActivity(intent);
             }
        });
    }

    @Override
    public int getItemCount() {
        return main_notice_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noticeTitle;
        TextView noticeDate;
        LinearLayout mainNotice;

        public ViewHolder(View view) {
            super(view);
            noticeTitle = (TextView)view.findViewById(R.id.noticeTitle);
            noticeDate = (TextView)view.findViewById(R.id.noticeDate);
            mainNotice = view.findViewById(R.id.mainNotice);

        }
    }
}
