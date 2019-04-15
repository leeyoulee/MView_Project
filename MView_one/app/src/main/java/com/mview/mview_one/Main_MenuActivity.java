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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mview.mview_one.login.Main_LoginActivity;
import com.mview.mview_one.main_fragment.CalendarFragment;
import com.mview.mview_one.main_fragment.attention.AttentionFragment;
import com.mview.mview_one.main_fragment.event.EventFragment;
import com.mview.mview_one.main_fragment.MainFragment;
import com.mview.mview_one.main_fragment.ProfileFragment;
import com.mview.mview_one.main_fragment.review.ReviewFragment;
import com.mview.mview_one.main_fragment.talk.TalkFragment;
import com.mview.mview_one.menu_fragment.eyebrow_freeFragment;
import com.mview.mview_one.menu_fragment.eyebrow_payFragment;
import com.mview.mview_one.menu_fragment.hair_freeFragment;
import com.mview.mview_one.menu_fragment.hair_payFragment;
import com.mview.mview_one.menu_fragment.makeup_freeFragment;
import com.mview.mview_one.menu_fragment.makeup_payFragment;
import com.mview.mview_one.menu_fragment.model_wantedFragment;
import com.mview.mview_one.menu_fragment.nail_freeFragment;
import com.mview.mview_one.menu_fragment.nail_payFragment;
import com.mview.mview_one.menu_fragment.skin_freeFragment;
import com.mview.mview_one.menu_fragment.skin_payFragment;
import com.mview.mview_one.menu_fragment.snap_freeFragment;
import com.mview.mview_one.menu_fragment.snap_payFragment;
import com.mview.mview_one.notice_qna.noticeFragment;
import com.mview.mview_one.notice_qna.qnaFragment;

public class Main_MenuActivity extends AppCompatActivity {

    Toolbar toolbar_main;
    public static Activity main_menuactivity;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        main_menuactivity = Main_MenuActivity.this;

        Intent intent = getIntent();
        String fragment_name = intent.getExtras().getString("fragment_name");
        Log.v("userName", MainActivity.userNickname);

        final ImageView toolbar_title = (ImageView) findViewById(R.id.toolbar_title);
        final Button CalendarBtn = (Button) findViewById(R.id.CalendarBtn);
        final Button eventBtn = (Button) findViewById(R.id.eventBtn);
        final Button profileBtn = (Button) findViewById(R.id.profileBtn);
        final Button reviewBtn = (Button) findViewById(R.id.reviewBtn);
        final Button talkBtn = (Button) findViewById(R.id.talkBtn);
        final LinearLayout main = (LinearLayout) findViewById(R.id.main);
        final RelativeLayout MainFragment = (RelativeLayout) findViewById(R.id.MainFragment);

        //Toolbar
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //true로 하면 뒤로가기 버튼 생성됨
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (fragment_name.equals("model_wantedFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new model_wantedFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("hair_freeFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new hair_freeFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("nail_freeFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new nail_freeFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("skin_freeFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new skin_freeFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("makeup_freeFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new makeup_freeFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("eyebrow_freeFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new eyebrow_freeFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("snap_freeFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new snap_freeFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("hair_payFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new hair_payFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("nail_payFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new nail_payFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("skin_payFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new skin_payFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("makeup_payFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new makeup_payFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("eyebrow_payFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new eyebrow_payFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("snap_payFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new snap_payFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("noticeFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new noticeFragment());
            fragmentTransaction.commit();
        } else if (fragment_name.equals("qnaFragment")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main, new qnaFragment());
            fragmentTransaction.commit();
        }

        toolbar_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                eventBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                profileBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                reviewBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                talkBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cal_dark), null, null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.gift_dark), null, null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profile_dark), null, null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.review_dark), null, null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.chat_dark), null, null);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.MainFragment, new MainFragment());
                fragmentTransaction.commit();
            }
        });

        CalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(View.GONE);
                CalendarBtn.setTextColor(getResources().getColor(R.color.colorDarkMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cal_light), null, null);
                eventBtn.setTextColor(getResources().getColor(R.color.colorMain));
                profileBtn.setTextColor(getResources().getColor(R.color.colorMain));
                reviewBtn.setTextColor(getResources().getColor(R.color.colorMain));
                talkBtn.setTextColor(getResources().getColor(R.color.colorMain));
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cal_dark), null, null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.gift_light), null, null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profile_light), null, null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.review_light), null, null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.chat_light), null, null);
                if (MainActivity.userNo.equals("userNo")) {
                    Intent intent = new Intent(Main_MenuActivity.this, Main_LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.MainFragment, new CalendarFragment());
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
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cal_light), null, null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.gift_dark), null, null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profile_light), null, null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.review_light), null, null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.chat_light), null, null);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.MainFragment, new EventFragment());
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
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cal_light), null, null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.gift_light), null, null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profile_dark), null, null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.review_light), null, null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.chat_light), null, null);
                if (MainActivity.userNo.equals("userNo")) {
                    Intent intent = new Intent(Main_MenuActivity.this, Main_LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.MainFragment, new ProfileFragment());
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
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cal_light), null, null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.gift_light), null, null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profile_light), null, null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.review_dark), null, null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.chat_light), null, null);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.MainFragment, new ReviewFragment());
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
                CalendarBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cal_light), null, null);
                eventBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.gift_light), null, null);
                profileBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profile_light), null, null);
                reviewBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.review_light), null, null);
                talkBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.chat_dark), null, null);
                if (MainActivity.userNo.equals("userNo")) {
                    Intent intent = new Intent(Main_MenuActivity.this, Main_LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.MainFragment, new TalkFragment());
                    fragmentTransaction.commit();
                }
            }
        });
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
                if (MainActivity.userNo.equals("userNo")) {
                    Intent intent = new Intent(Main_MenuActivity.this, Main_LoginActivity.class);
                    startActivity(intent);
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, new AttentionFragment());
                    fragmentTransaction.commit();
                }
                return true;
            }
            case android.R.id.home: {
                Intent intent = new Intent(Main_MenuActivity.this, MenuActivity.class);
                intent.putExtra("activity_name", "Main_MenuActivity");
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


