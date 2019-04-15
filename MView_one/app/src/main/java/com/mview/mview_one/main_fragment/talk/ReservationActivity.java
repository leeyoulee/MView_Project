package com.mview.mview_one.main_fragment.talk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.MainActivity;
import com.mview.mview_one.R;
import com.mview.mview_one.main_fragment.CalendarFragment;
import com.mview.mview_one.main_fragment.CalenderDeco.EventDecorator;
import com.mview.mview_one.main_fragment.CalenderDeco.SaturdayDecorator;
import com.mview.mview_one.main_fragment.CalenderDeco.SundayDecorator;
import com.mview.mview_one.main_fragment.CalenderDeco.oneDayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class ReservationActivity extends AppCompatActivity {

    String shopNo;
    String userNo;
    MaterialCalendarView calendarView;
    String[] rsvDate;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Intent intent = getIntent();
        shopNo = intent.getExtras().getString("shopNo");
        userNo = intent.getExtras().getString("userNo");

        calendarView = findViewById(R.id.calendarView);

        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new oneDayDecorator(ReservationActivity.this));

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();


                final AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);


                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                final String shot_Day = Year + "-" + Month + "-" + Day;
                builder.setTitle("예약 확인");
                builder.setMessage("예약하시겠습니까?");
                builder.setPositiveButton("네",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txt = shot_Day;
                                Log.e("Day test", shot_Day);
                                setData("http://alsrud55399.cafe24.com/updatedate.php");
                                Toast.makeText(getApplicationContext(), "예약 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                calendarView.clearSelection();
            }
        });
    }

    public void setData(String url)  // 채팅을 하는 버튼입니다. 버튼을 누를시 메시지가 db에 저장됩니다.
    {
        class setDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                String param = "id=" + userNo + "&company=" + shopNo + "&date=" + txt;
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
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        Log.e("json  ", json);
                    }


                } catch (Exception e) {
                    return null;
                }
                return null;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), "예약되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        setDataJSON s = new setDataJSON();
        s.executeOnExecutor(PriorityAsyncTask.THREAD_POOL_EXECUTOR, url);
    }
}

