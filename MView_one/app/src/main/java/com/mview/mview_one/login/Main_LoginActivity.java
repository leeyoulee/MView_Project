package com.mview.mview_one.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.MainActivity;
import com.mview.mview_one.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main_LoginActivity extends AppCompatActivity {

    EditText userId;
    EditText userPw;
    String userNo;
    String userType;
    String userNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        final ImageView login_image = findViewById(R.id.login_image);
        final LinearLayout login_layout = findViewById(R.id.login_layout);
        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        Button loginBtn = findViewById(R.id.loginBtn);
        TextView idFind = findViewById(R.id.idFind);
        TextView pwFind = findViewById(R.id.pwFind);
        TextView registerBtn = findViewById(R.id.registerBtn);
        TextView mainBtn = findViewById(R.id.mainBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginloadData();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Main_LoginActivity.this, RegisterActivity.class);
                Main_LoginActivity.this.startActivity(registerIntent);
            }
        });

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_LoginActivity.this, MainActivity.class);
                intent.putExtra("userNo","userNo");
                intent.putExtra("userType","userType");
                intent.putExtra("userNickname","userNickname");
                startActivity(intent);
                finish();
            }
        });

        idFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_LoginActivity.this, IdSearchActivity.class);
                startActivity(intent);
            }
        });

        pwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_LoginActivity.this, PwSearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/login.php?userId=" +userId.getText().toString() + "& userPw=" + userPw.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    if(array.length() > 0){
                        for (int i = 0; i < array.length(); i++){
                            JSONObject object = array.getJSONObject(i);
                            userNo = object.getString("userNo").toString();
                            userType = object.getString("userType").toString();
                            userNickname = object.getString("userNickname").toString();
                        }
                        Intent intent = new Intent(Main_LoginActivity.this, MainActivity.class);
                        intent.putExtra("userNo",userNo);
                        intent.putExtra("userType",userType);
                        intent.putExtra("userNickname",userNickname);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Main_LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main_LoginActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Main_LoginActivity.this);
        requestQueue.add(stringRequest);
    }

}