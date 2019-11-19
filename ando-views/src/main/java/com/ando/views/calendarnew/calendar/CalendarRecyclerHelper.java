package com.ando.views.calendarnew.calendar;

import android.content.Context;
import android.widget.AbsListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by huang on 2017/11/22.
 */

public class CalendarRecyclerHelper {

    private CalendarRecyclerHelper(){}

    public static void init(Context context, RecyclerView recyclerView, CalendarAdapter adapter){
        init(context,recyclerView,adapter,null);
    }


    public static void init(Context context, RecyclerView recyclerView, CalendarAdapter adapter, final CalendarView.OnCalendarPageChanged onCalendarPageChanged){
        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    CalendarView calendarView = (CalendarView) recyclerView.getChildAt(0);
                    CustomDate showDate =  calendarView.getShowDate();
                    if(onCalendarPageChanged != null) {
                        onCalendarPageChanged.onPageChanged(showDate);
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        //返回当前月
        if(onCalendarPageChanged != null){
            onCalendarPageChanged.onPageChanged(new CustomDate());
        }
        recyclerView.setAdapter(adapter);
        manager.scrollToPosition(Integer.MAX_VALUE/2);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
    }


}
