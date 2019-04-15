package com.mview.mview_one.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText userId;
    EditText userPw;
    EditText userNickname;
    EditText userPhone;
    TextView idCheck;
    Button registerBtn;
    Spinner userSpinner;
    ArrayAdapter userAdapter;
    String idResult = "idCheck";
    String userType;
    String userType_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        userNickname = findViewById(R.id.userNickname);
        userPhone = findViewById(R.id.userPhone);
        idCheck = findViewById(R.id.idCheck);
        registerBtn = findViewById(R.id.registerBtn);
        userSpinner = (Spinner) findViewById(R.id.userSpinner);
        userAdapter = ArrayAdapter.createFromResource(this, R.array.user, android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(userAdapter);

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                userType = userSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        idCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userId.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (Pattern.matches("(^[A-Za-z0-9]{4,12}$)",userId.getText().toString())){
                    idCheckloadData();
                } else {
                    Toast.makeText(RegisterActivity.this, "아이디를 4-12자 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idResult.equals("idCheck")) {
                    Toast.makeText(RegisterActivity.this, "아이디 중복체크를 해주세요.", Toast.LENGTH_SHORT).show();
                } else if (idResult.equals("false") || userType.equals("회원구분") || userPw.getText().toString().equals("") || userNickname.getText().toString().equals("") || userPhone.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (userPw.length() < 6){
                    Toast.makeText(RegisterActivity.this, "비밀번호를 6자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else{
                    if(userType.equals("개인")){
                        userType_data = "0";
                    }else if(userType.equals("업체")){
                        userType_data = "1";
                    }
                    registerloadData();
                }
            }
        });
    }


    private void idCheckloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/register_Idcheck.php?userId=" + userId.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    if (array.length() == 0) {
                        Toast.makeText(RegisterActivity.this, "사용할 수 있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        idCheck.setEnabled(false);
                        userId.setEnabled(false);
                        idResult = "true";
                    } else {
;                        Toast.makeText(RegisterActivity.this, "사용할 수 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        idResult = "false";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
    }

    private void registerloadData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://alsrud55399.cafe24.com/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Toast.makeText(RegisterActivity.this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId", userId.getText().toString());
                params.put("userPw", userPw.getText().toString());
                params.put("userNickname", userNickname.getText().toString());
                params.put("userPhone", userPhone.getText().toString());
                params.put("userType",userType_data);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
    }

}
