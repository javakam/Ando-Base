package com.ando.views.calendarnew;

import com.ando.views.calendarnew.calendar.CalendarAdapter;
import com.ando.views.calendarnew.calendar.CalendarView;

/**
 * Created by huang on 2017/11/22.
 */

public class CustomCalendarAdapter extends CalendarAdapter {
    private CustomTextDrawFormat format;

    public CustomCalendarAdapter(int mode) {
        super(mode);
    }

    public CustomCalendarAdapter() {
    }

    @Override
    protected void decorateCalendarView(CalendarView calendarView) {
        if(format == null){
            format = new CustomTextDrawFormat(mContext);
        }
        calendarView.addDrawFormat(format);
    }
}
