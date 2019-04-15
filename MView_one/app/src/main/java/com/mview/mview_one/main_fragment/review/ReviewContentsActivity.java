package com.mview.mview_one.main_fragment.review;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class ReviewContentsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView shopName;
    TextView category;
    TextView reviewGrade;
    TextView pay;
    TextView modelDate;
    TextView reviewContents;
    TextView reviewViews;
    String reviewNo;
    String reviewView;
    int views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_contents);

        Intent intent = getIntent();
        reviewNo = intent.getExtras().getString("reviewNo");
        reviewView = intent.getExtras().getString("reviewViews");
        views = Integer.parseInt(reviewView)+1;
        Log.v("test",views+"");

        viewsloadData();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //true로 하면 뒤로가기 버튼 생성됨
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        shopName = findViewById(R.id.shopName);
        category  = findViewById(R.id.category );
        reviewGrade   = findViewById(R.id.reviewGrade  );
        pay  = findViewById(R.id.pay );
        modelDate = findViewById(R.id.modelDate);
        reviewContents = findViewById(R.id.reviewContents);
        reviewViews = findViewById(R.id.reviewViews);

        reviewloadData();
    }

    private void viewsloadData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://alsrud55399.cafe24.com/views.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReviewContentsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("views", String.valueOf(views));
                params.put("reviewNo", reviewNo);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ReviewContentsActivity.this);
        requestQueue.add(stringRequest);
    }

    private void reviewloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/review_contents.php?reviewNo=" +reviewNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    Log.v("response",response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        shopName.setText(object.getString("SHOP_INFO.shopName"));
                        category.setText(object.getString("MODEL_INFO.modelField"));
                        reviewGrade.setText(object.getString("REVIEW.reviewGrade")) ;
                        reviewContents.setText(object.getString("REVIEW.reviewContents"));
                        reviewViews.setText(object.getString("REVIEW.reviewViews"));

                        String dateResult = object.getString("REVIEW.modelDate");
                        String replace = dateResult.replace("-","/");
                        modelDate.setText(replace);

                        String payResult = object.getString("MODEL_INFO.modelPrice");
                        if (payResult.equals("0")) {
                            pay.setText("무료");
                        } else {
                            pay.setText(payResult+"원");
                        }

/*                        String result = object.getString("noticeContents");
                        String replace = result.replace("\\n","\n");
                        noticeContents.setText(replace);*/
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReviewContentsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ReviewContentsActivity.this);
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
