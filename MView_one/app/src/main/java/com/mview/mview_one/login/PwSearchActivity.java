package com.mview.mview_one.login;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class PwSearchActivity extends AppCompatActivity {

    String userIdResult;
    String userPhoneResult;
    LinearLayout pw_1;
    public static String userNo;
    String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_search);

        final EditText userId = findViewById(R.id.userId);
        final EditText userPhone = findViewById(R.id.userPhone);
        Button pwSearchBtn = findViewById(R.id.pwSearchBtn);
        pw_1 = findViewById(R.id.pw_1);

        pwSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userIdResult = userId.getText().toString();
                userPhoneResult = userPhone.getText().toString();

                pwsearch_1();
            }
        });
    }

    private void pwsearch_1() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/pwsearch_1.php?userPhone=" + userPhoneResult +"&userId=" + userIdResult, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    if(array.length() == 0 ){
                        Toast.makeText(PwSearchActivity.this, "해당하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for (int i = 0; i < array.length(); i++){
                            JSONObject obj = array.getJSONObject(i);
                            userNo = obj.getString("userNo").toString();
                        }
                        pw_1.setVisibility(View.GONE);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.pw_2, new PwResetFragment());
                        fragmentTransaction.commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PwSearchActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(PwSearchActivity.this);
        requestQueue.add(stringRequest);
    }
}
