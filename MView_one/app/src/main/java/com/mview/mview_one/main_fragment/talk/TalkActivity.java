package com.mview.mview_one.main_fragment.talk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mview.mview_one.MainActivity;
import com.mview.mview_one.R;
import com.mview.mview_one.login.RegisterActivity;
import com.mview.mview_one.main_fragment.review.ReviewWriteActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TalkActivity extends AppCompatActivity {

    Toolbar toolbar_main;
    ListView listview;
    String myJSON;
    EditText text;
    Button sendBtn;
    chat_adapter adapter;
    Date dateNow;
    getDataThread gdt;
    String shopNo;
    String userNo;
    String userNo_result;
    String Name_chat;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_CALENDAR = "calendar";

    Toast toast, toast2;
    boolean stop = false;
    JSONArray peoples = null, calendar = null;
    boolean first = true;
    String name, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        Intent intent = getIntent();
        shopNo = intent.getExtras().getString("shopNo_chat");
        userNo = intent.getExtras().getString("userNo_chat");
        Name_chat = intent.getExtras().getString("Name_chat");

        Log.v("Name_chat",Name_chat);

        if (MainActivity.userType.equals("1")) {
            id = userNo;
            name = MainActivity.shopName_result;
        } else if (MainActivity.userType.equals("0")) {
            id = shopNo;
            name = MainActivity.userNickname;
        }

        //Toolbar
        toolbar_main = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //true로 하면 뒤로가기 버튼 생성됨
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_main.setTitle(Name_chat);

        listview = (ListView) findViewById(R.id.listview);
        adapter = new chat_adapter();
        listview.setAdapter(adapter);

        text = (EditText) findViewById(R.id.text);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        long now = System.currentTimeMillis();
        dateNow = new Date(now);

        //listview.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        SimpleDateFormat sdf = new SimpleDateFormat(("yyyy년 MM월 dd일"));
        String getTime = sdf.format(dateNow);

        toast = Toast.makeText(TalkActivity.this, getTime, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        openData("http://alsrud55399.cafe24.com/openchat.php");


        sendBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData("http://alsrud55399.cafe24.com/chatting.php");
                int txttype = 0;
                if (userNo_result == null) {
                    txttype = 1;
                    text.setGravity(Gravity.LEFT);
                }
                else if (userNo_result.equals(id)) {
                    txttype = 0;
                    text.setGravity(Gravity.RIGHT);
                } else {
                    txttype = 1;
                    text.setGravity(Gravity.LEFT);
                }
                adapter.add(id, name, text.getText().toString(), txttype);
                adapter.notifyDataSetChanged();
                text.setText(null);
            }
        });

        gdt = new getDataThread();
        gdt.start();
    }


    public void onStop() {

        super.onStop();
        gdt.interrupt();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }

            case R.id.action_book:
                Intent intent = new Intent(TalkActivity.this, ReservationActivity.class);
                intent.putExtra("shopNo", shopNo);
                intent.putExtra("userNo", userNo);
                startActivity(intent);
                finish();

            case R.id.action_comment:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("리뷰를 작성하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(TalkActivity.this, ReviewWriteActivity.class);
                                intent.putExtra("shopNo", shopNo);
                                intent.putExtra("userNo", userNo);
                                startActivity(intent);
                            }
                        });
                builder.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (MainActivity.userType.equals("1")) {
            getMenuInflater().inflate(R.menu.menu_company, menu);
            return true;
        } else {
            getMenuInflater().inflate(R.menu.menu_user, menu);
            return true;
        }
    }

    public void onBackPressed() {
        stop = true;
        finish();
        super.onBackPressed();

    }


    class getDataThread extends Thread {
        public void run() {
            while (!stop) {
                getData("http://alsrud55399.cafe24.com/chatupdate.php");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Thread.interrupted())
                    break;
            }
        }
    }

    protected void showList() {
        try {
            if (myJSON == null) {

            } else {
                JSONObject jsonObj = new JSONObject(myJSON);
                peoples = jsonObj.getJSONArray(TAG_RESULTS);

                for (int i = 0; i < peoples.length(); i++) {
                    JSONObject c = peoples.getJSONObject(i);
                    userNo_result = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String message = c.getString(TAG_MESSAGE);
                    if (first) {
                        LayoutInflater inflater = getLayoutInflater();

                        View layout = inflater.inflate(R.layout.toast_board, (ViewGroup) findViewById(R.id.toast_layout_root));

                        String date = jsonObj.getJSONArray(TAG_CALENDAR).getJSONObject(0).getString("date");
                        if (date.equals("null")) {
                        }
                        else{
                            String[] time = date.split("-");
                            int year = Integer.parseInt(time[0]);
                            int month = Integer.parseInt(time[1]);
                            int dayy = Integer.parseInt(time[2]);
                            TextView messageTextView = (TextView) layout.findViewById(R.id.text);

                            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);

                            messageTextView.setText(year + "년 " + month + "월 " + dayy + "일" + "에 예약되셨습니다!");

                            toast2 = new Toast(getApplicationContext());

                            toast2.setDuration(Toast.LENGTH_LONG);

                            toast2.setView(layout);
                            toast2.show();

                            first = false;
                        }
                    }
                    int txttype = 0;
                    if (userNo_result.equals(id)) {
                        txttype = 0;
                        text.setGravity(Gravity.RIGHT);
                    } else {
                        txttype = 1;
                        text.setGravity(Gravity.LEFT);
                    }
                    adapter.add(userNo_result, name, message, txttype);
                    adapter.notifyDataSetChanged();
                    listview.setSelection(adapter.getCount() - 1);
                    Log.e("result", "채팅이 업데이트 되었습니다.");
                }
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void openData(String url)  // 가장 처음 채팅방을 열때, 채팅방이 처음 생기는 거면 table을 새로 생성하며, 기존에 존재할 시, 기존 채팅내역 공개
    {
        class openDataJSON extends AsyncTask<String, Void, String> {
            String param = "user=" + userNo + "&company=" + shopNo; // 1:1 매칭이므로 user1 user2의 아이디가 들어갑니다. 또한 채팅은 업체 -> 사용자인 단방향 통신이므로 중복테이블 생성은 존재할 수 없습니다.

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    OutputStream outs = conn.getOutputStream();
                    outs.write(param.getBytes("UTF-8"));
                    outs.flush();
                    //Log.e("param ",param);
                    outs.close();

                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            protected void onPostExecute(String result) {
                if (result.equals("clear2")) {
                    Log.e("result", "처음 채팅창이 생성되었습니다.");
                } else {
                    myJSON = result;
                    showList();

                    Log.e("answer", result);
                    Log.e("result", "기존 채팅창이 열렸습니다.");
                }
            }
        }
        openDataJSON o = new openDataJSON();
        o.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    public void setData(String url)  // 채팅을 하는 버튼입니다. 버튼을 누를시 메시지가 db에 저장됩니다.
    {
        class setDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                String param = "user=" + userNo + "&company=" + shopNo + "&name=" + name + "&message=" + text.getText() + "&type=" + MainActivity.userType; // 1:1 매칭이므로 user1 user2의 아이디가 들어갑니다.
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    OutputStream outs = conn.getOutputStream();
                    outs.write(param.getBytes("UTF-8"));
                    outs.flush();
                    outs.close();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    //Log.e("param : ", param);
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        Log.e("json  ", json);
                    }
                } catch (Exception e) {
                    return null;
                }
                Log.e("setData", "입력한 채팅이 전송되었습니다.");
                return null;
            }
        }
        setDataJSON s = new setDataJSON();
        s.executeOnExecutor(PriorityAsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    public void getData(String url) {  //말그대로 데이터를 불러들입니다. 계속해서 채팅내역을 새로고침합니다.
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String param = "user=" + userNo + "&company=" + shopNo;
                String uri = params[0];
                Log.e("...", "채팅 업데이트 중입니다!");
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestMethod("GET");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    OutputStream outs = conn.getOutputStream();
                    outs.write(param.getBytes("UTF-8"));
                    outs.flush();
                    //Log.e("param : ",param);
                    outs.close();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String json;

                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    if (sb != null) {
                        //Log.e("please",sb.toString());
                    }
                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
                Log.e("..", myJSON);
                Log.e("...", "채팅 업데이트 완료!");
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}