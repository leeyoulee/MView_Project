package com.mview.mview_one.main_fragment.CalenderDeco;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

import static com.mview.mview_one.R.color.colorGray;

public class SundayDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public SundayDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == Calendar.SUNDAY;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(colorGray));
    }
}
