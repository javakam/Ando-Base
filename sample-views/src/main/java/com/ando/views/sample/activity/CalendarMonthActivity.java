package com.ando.views.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ando.views.calendarnew.CustomCalendarAdapter;
import com.ando.views.calendarnew.calendar.CalendarRecyclerHelper;
import com.ando.views.calendarnew.calendar.CalendarView;
import com.ando.views.calendarnew.calendar.CustomDate;
import com.ando.views.sample.R;

/**
 * Created by huang on 2017/11/9.
 */

public class CalendarMonthActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TextView showTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_new);
        findViewById(R.id.img_cal_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        showTv = (TextView) findViewById(R.id.tv_show_date);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        CalendarRecyclerHelper.init(this, recyclerView, new CustomCalendarAdapter(), new CalendarView.OnCalendarPageChanged() {

            @Override
            public void onPageChanged(CustomDate showDate) {
                showTv.setText(String.format("%d年%d月", showDate.year, showDate.month));
            }
        });
    }
}
