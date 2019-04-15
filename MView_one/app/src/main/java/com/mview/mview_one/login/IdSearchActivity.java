package com.mview.mview_one.login;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class IdSearchActivity extends AppCompatActivity {

    EditText userPhone;
    String idResult;
    String phoneResult;
    String result;
    private RecyclerView idView;
    private ArrayList idViewdatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_search);

        userPhone = findViewById(R.id.userPhone);
        TextView closeBtn = findViewById(R.id.closeBtn);
        Button idSearchBtn = findViewById(R.id.idSearchBtn);

        idView = findViewById(R.id.idView);
        idView.setHasFixedSize(true);
        LinearLayoutManager layoutManager_notice = new LinearLayoutManager(IdSearchActivity.this);
        layoutManager_notice.setOrientation(LinearLayoutManager.VERTICAL);
        idView.setLayoutManager(layoutManager_notice);
        idViewdatas = new ArrayList<>();

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        idSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idViewdatas.clear();
                phoneResult = userPhone.getText().toString();
                idSearchData();

            }
        });
    }

    private void idSearchData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/idsearch.php?userPhone=" + phoneResult, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    if(array.length() == 0 ){
                        IdSearch_data IdSearch_Data = new IdSearch_data("null");
                        idViewdatas.add(IdSearch_Data);
                    }
                    else{
                        for (int i = 0; i < array.length(); i++){
                            JSONObject obj = array.getJSONObject(i);
                            idResult = obj.getString("userId").toString();
                            IdSearch_data IdSearch_Data = new IdSearch_data(idResult);
                            idViewdatas.add(IdSearch_Data);
                        }
                    }
                        IdSearch_adapter adapter = new IdSearch_adapter(IdSearchActivity.this, idViewdatas);
                        adapter.notifyDataSetChanged();
                        idView.setAdapter(adapter);
                        idView.invalidate();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IdSearchActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(IdSearchActivity.this);
        requestQueue.add(stringRequest);
    }
}
