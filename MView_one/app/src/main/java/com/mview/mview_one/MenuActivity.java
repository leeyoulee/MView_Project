package com.mview.mview_one;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.menu_fragment.hair_freeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Intent intent = getIntent();
        final String activity_name = intent.getExtras().getString("activity_name");

        final MainActivity main = (MainActivity) MainActivity.mainactivity;
        final Main_MenuActivity main_menu = (Main_MenuActivity) Main_MenuActivity.main_menuactivity;

        final TextView modelBtn = findViewById(R.id.modelBtn);

        final Button hairBtn_free = (Button) findViewById(R.id.hairBtn_free);
        final Button nailBtn_free = (Button) findViewById(R.id.nailBtn_free);
        final Button skinBtn_free = (Button) findViewById(R.id.skinBtn_free);
        final Button makeupBtn_free = (Button) findViewById(R.id.makeupBtn_free);
        final Button eyebrowBtn_free = (Button) findViewById(R.id.eyebrowBtn_free);
        final Button snapBtn_free = (Button) findViewById(R.id.snapBtn_free);


        final Button hairBtn_pay = (Button) findViewById(R.id.hairBtn_pay);
        final Button nailBtn_pay = (Button) findViewById(R.id.nailBtn_pay);
        final Button skinBtn_pay = (Button) findViewById(R.id.skinBtn_pay);
        final Button makeupBtn_pay = (Button) findViewById(R.id.makeupBtn_pay);
        final Button eyebrowBtn_pay = (Button) findViewById(R.id.eyebrowBtn_pay);
        final Button snapBtn_pay = (Button) findViewById(R.id.snapBtn_pay);


        final Button noticeBtn = (Button) findViewById(R.id.noticeBtn);
        final Button qnaBtn = (Button) findViewById(R.id.qnaBtn);
        final Button questionBtn = (Button) findViewById(R.id.questionBtn);


        //Toolbar
        Toolbar toolbar_menu = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar_menu);
        getSupportActionBar().setTitle("카테고리");

        modelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "model_wantedFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        hairBtn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "hair_freeFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        nailBtn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "nail_freeFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        skinBtn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "skin_freeFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        makeupBtn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "makeup_freeFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        eyebrowBtn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "eyebrow_freeFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        snapBtn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "snap_freeFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        hairBtn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "hair_payFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        nailBtn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "nail_payFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        skinBtn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "skin_payFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        makeupBtn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "makeup_payFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        eyebrowBtn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "eyebrow_payFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        snapBtn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "snap_payFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        noticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "noticeFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        qnaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "qnaFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

        questionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Main_MenuActivity.class);
                intent.putExtra("fragment_name", "questionFragment");
                startActivity(intent);
                if (activity_name.equals("MainActivity")) {
                    main.finish();
                    finish();
                } else if (activity_name.equals("Main_MenuActivity")) {
                    main_menu.finish();
                    finish();
                }
            }
        });

    }

    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_category, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.closeBtn: {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
