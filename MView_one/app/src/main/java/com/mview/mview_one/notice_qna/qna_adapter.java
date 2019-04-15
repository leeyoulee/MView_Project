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

import java.util.ArrayList;

public class qna_adapter extends RecyclerView.Adapter<qna_adapter.ViewHolder> {
    private ArrayList<qna_data> qna_data;
    private Context context;

    public qna_adapter(Context context, ArrayList<qna_data> qna_data) {
        this.context = context;
        this.qna_data = qna_data;
    }

    @Override
    public qna_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.qna_item, viewGroup, false);
        return new qna_adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final qna_adapter.ViewHolder viewHolder, final int i) {
        viewHolder.qnaTitle.setText(qna_data.get(i).getQnaTitle_data());
        viewHolder.qnaFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QnaContentsActivity.class);
                intent.putExtra("qnaNo", qna_data.get(i).getQnaNo_data());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return qna_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView qnaTitle;
        LinearLayout qnaFragment;

        public ViewHolder(View view) {
            super(view);
            qnaTitle = (TextView) view.findViewById(R.id.qnaTitle);
            qnaFragment = view.findViewById(R.id.qnaFragment);
        }
    }
}