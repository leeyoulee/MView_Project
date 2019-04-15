package com.mview.mview_one.main_fragment.CalenderDeco;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import com.mview.mview_one.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

import static com.mview.mview_one.R.color.colorDarkMain;
import static com.mview.mview_one.R.color.colorGray;

public class EventDecorator implements DayViewDecorator {

    private HashSet<CalendarDay> dates;

    public EventDecorator(Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
        view.addSpan(new ForegroundColorSpan(colorDarkMain));
        view.addSpan(new DotSpan(7, colorGray));
    }
}