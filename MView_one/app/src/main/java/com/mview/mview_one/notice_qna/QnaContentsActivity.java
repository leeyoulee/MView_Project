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

public class QnaContentsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView qnaTitle;
    TextView qnaContents;
    String qnaNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_contents);

        Intent intent = getIntent();
        qnaNo = intent.getExtras().getString("qnaNo");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //true로 하면 뒤로가기 버튼 생성됨
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        qnaTitle = findViewById(R.id.qnaTitle);
        qnaContents = findViewById(R.id.qnaContents);

        qnaloadData();
    }

    private void qnaloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/qna_contents.php?qnaNo=" + qnaNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        Log.v("test",object.getString("qnaTitle") + object.getString("qnaContents"));
                        qnaTitle.setText(object.getString("qnaTitle"));
                        String result = object.getString("qnaContents");
                        String replace = result.replace("\\n","\n");
                        qnaContents.setText(replace);
                        //qnaContents.setText("aa\nbb");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QnaContentsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(QnaContentsActivity.this);
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
