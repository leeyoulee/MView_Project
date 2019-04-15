package com.mview.mview_one.notice_qna;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mview.mview_one.R;
import com.mview.mview_one.main_fragment.main.main_notice_data;

import java.util.ArrayList;

public class notice_adapter extends RecyclerView.Adapter<notice_adapter.ViewHolder>{
    private ArrayList<notice_data> notice_data;
    private Context context;

    public notice_adapter(Context context, ArrayList<main_notice_data> main_notice_data) {
        this.context = context;
        this.notice_data = notice_data;
    }

    @Override
    public notice_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_item, viewGroup, false);
        return new notice_adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final notice_adapter.ViewHolder viewHolder, final int i) {
        viewHolder.noticeTitle.setText(notice_data.get(i).getNoticeTitle_data());
        viewHolder.noticeDate.setText(notice_data.get(i).getNoticeDate_data());
        viewHolder.FragmentNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoticeContentsActivity.class);
                intent.putExtra("noticeNo",notice_data.get(i).getNoticeNo_data());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return notice_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noticeTitle;
        TextView noticeDate;
        LinearLayout FragmentNotice;

        public ViewHolder(View view) {
            super(view);
            noticeTitle = (TextView)view.findViewById(R.id.noticeTitle);
            noticeDate = (TextView)view.findViewById(R.id.noticeDate);
            FragmentNotice = view.findViewById(R.id.FragmentNotice);
        }
    }
}
