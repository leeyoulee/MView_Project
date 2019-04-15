package com.mview.mview_one.notice_qna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NoticeContentsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView noticeTitle;
    TextView noticeDate;
    TextView noticeContents;
    String noticeNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_contents);

        Intent intent = getIntent();
        noticeNo = intent.getExtras().getString("noticeNo");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //true로 하면 뒤로가기 버튼 생성됨
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        noticeTitle = findViewById(R.id.noticeTitle);
        noticeDate = findViewById(R.id.noticeDate);
        noticeContents = findViewById(R.id.noticeContents);

        noticeloadData();
    }

    private void noticeloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/notice_contents.php?noticeNo=" +noticeNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        noticeTitle.setText(object.getString("noticeTitle"));
                        noticeDate.setText(object.getString("noticeDate"));
                        String result = object.getString("noticeContents");
                        String replace = result.replace("\\n","\n");
                        noticeContents.setText(replace);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NoticeContentsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(NoticeContentsActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
