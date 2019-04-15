package com.mview.mview_one.main_fragment.review;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReviewWriteActivity extends AppCompatActivity {


    Toolbar toolbar;
    EditText reviewContents;
    Button writeBtn;
    String shopNo;
    String userNo;
    RatingBar ratingBar;
    String ratingGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        Intent intent = getIntent();
        shopNo = intent.getExtras().getString("shopNo");
        userNo = intent.getExtras().getString("userNo");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //true로 하면 뒤로가기 버튼 생성됨
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ratingBar = findViewById(R.id.ratingBar);
        reviewContents = findViewById(R.id.reviewContents);
        writeBtn = findViewById(R.id.writeBtn);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingGrade = Float.toString(rating);
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if (reviewContents.getText().toString().equals(false)) {
                    Toast.makeText(ReviewWriteActivity.this, "후기를 작성해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    reviewwriteData();
                }
            }
        });
    }

    private void reviewwriteData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://alsrud55399.cafe24.com/review_write.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReviewWriteActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("shopNo", shopNo);
                params.put("userNo", userNo);
                params.put("ratingGrade", ratingGrade);
                params.put("reviewContents", reviewContents.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ReviewWriteActivity.this);
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