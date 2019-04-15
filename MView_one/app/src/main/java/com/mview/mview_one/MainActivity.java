package com.mview.mview_one;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.login.Main_LoginActivity;
import com.mview.mview_one.main_fragment.attention.AttentionFragment;
import com.mview.mview_one.main_fragment.CalendarFragment;
import com.mview.mview_one.main_fragment.event.EventFragment;
import com.mview.mview_one.main_fragment.MainFragment;
import com.mview.mview_one.main_fragment.ProfileFragment;
import com.mview.mview_one.main_fragment.review.ReviewFragment;
import com.mview.mview_one.main_fragment.talk.TalkFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar_main;
    public static Activity mainactivity;
    public static String userNo;
    public static String userType;
    public static String userNickname;
    public static String shopNo_result;
    public static String shopName_result;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainactivity = MainActivity.this;

        Intent intent = getIntent();
        userNo = intent.getExtras().getString("userNo");
        userType = intent.getExtras().getString("userType");
        userNickname = intent.getExtras().getString("userNickname");

        if( userType.equals("1")){
            shopNoloadData();
        }

        final ImageView toolbar_title = (ImageView) findViewById(R.id.toolbar_title);
        final Button CalendarBtn = (Button) findViewById(R.id.CalendarBtn);
        final Button eventBtn = (Button) findViewById(R.id.eventBtn);
        final Button profileBtn = (Button) findViewById(R.id.profileBtn);
        final Button reviewBtn = (Button) findViewById(R.id.reviewBtn);
        final Button talkBtn = (Button) findViewById(R.id.talkBtn);
        final LinearLayout main = (LinearLayout) findViewById(R.id.main);
        final RelativeLayout fragment = (RelativeLayout) findViewById(R.id.fragment);

        //Toolbar
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //true로 하면 뒤로가기 버튼 생성됨
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main, new MainFragment());
        fragmentTransaction.commit();

        toolbar_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                eventBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                profileBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                reviewBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                talkBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.cal_dark),null,null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.gift_dark),null,null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.profile_dark),null,null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.review_dark),null,null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.chat_dark),null,null);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new MainFragment());
                fragmentTransaction.commit();
            }
        });

        CalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(View.GONE);
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                eventBtn.setTextColor(getResources().getColor(R.color.colorMain));
                profileBtn.setTextColor(getResources().getColor(R.color.colorMain));
                reviewBtn.setTextColor(getResources().getColor(R.color.colorMain));
                talkBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.cal_dark),null,null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.gift_light),null,null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.profile_light),null,null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.review_light),null,null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.chat_light),null,null);
                if (userNo.equals("userNo")) {
                    Intent intent = new Intent(MainActivity.this,Main_LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, new CalendarFragment());
                    fragmentTransaction.commit();
                }
            }
        });

        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(View.GONE);
                eventBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorMain));
                profileBtn.setTextColor(getResources().getColor(R.color.colorMain));
                reviewBtn.setTextColor(getResources().getColor(R.color.colorMain));
                talkBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.cal_light),null,null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.gift_dark),null,null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.profile_light),null,null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.review_light),null,null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.chat_light),null,null);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new EventFragment());
                fragmentTransaction.commit();
            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(View.GONE);
                profileBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                eventBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorMain));
                reviewBtn.setTextColor(getResources().getColor(R.color.colorMain));
                talkBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.cal_light),null,null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.gift_light),null,null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.profile_dark),null,null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.review_light),null,null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.chat_light),null,null);
                if (userNo.equals("userNo")) {
                    Intent intent = new Intent(MainActivity.this,Main_LoginActivity.class);
                    startActivity(intent);
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, new ProfileFragment());
                    fragmentTransaction.commit();
                }
            }
        });


        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(View.GONE);
                reviewBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                eventBtn.setTextColor(getResources().getColor(R.color.colorMain));
                profileBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorMain));
                talkBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.cal_light),null,null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.gift_light),null,null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.profile_light),null,null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.review_dark),null,null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.chat_light),null,null);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ReviewFragment());
                fragmentTransaction.commit();
            }
        });


        talkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(View.GONE);
                talkBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                eventBtn.setTextColor(getResources().getColor(R.color.colorMain));
                profileBtn.setTextColor(getResources().getColor(R.color.colorMain));
                reviewBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.cal_light),null,null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.gift_light),null,null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.profile_light),null,null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.review_light),null,null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.chat_dark),null,null);
                if (userNo.equals("userNo")) {
                    Intent intent = new Intent(MainActivity.this,Main_LoginActivity.class);
                    startActivity(intent);
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, new TalkFragment());
                    fragmentTransaction.commit();
                }
            }
        });

    }

    private void shopNoloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/shopno_find.php?userNo="+ MainActivity.userNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        shopNo_result = obj.getString("SHOP_INFO.shopNo").toString();
                        shopName_result = obj.getString("SHOP_INFO.shopName").toString();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }


    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.attentionBtn: {
                if (userNo.equals("userNo")) {
                    Intent intent = new Intent(MainActivity.this,Main_LoginActivity.class);
                    startActivity(intent);
                }else{
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, new AttentionFragment());
                    fragmentTransaction.commit();
                }
                return true;
            }
            case android.R.id.home: {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("activity_name", "MainActivity");
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


