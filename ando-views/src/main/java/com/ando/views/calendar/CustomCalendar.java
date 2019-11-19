package com.ando.views.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.Size;

import com.ando.views.R;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: CustomCalendar
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/1/8  9:22
 */
public class CustomCalendar extends FrameLayout {

    private static final int SCHEME_POINT_COLOR = Color.parseColor("#FFA800");
    private TextView mTvYearMonth;
    private String mDateFormat;
    private CalendarView mCalendarView;
    private CallBack mCallBack;

    public CustomCalendar(Context context) {
        this(context, null, 0);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setClipChildren(false);
        View v = inflate(context, R.layout.item_calendar, null);
        mTvYearMonth = v.findViewById(R.id.tv_current_year_month);
        mCalendarView = v.findViewById(R.id.calendarView);

        v.findViewById(R.id.iv_calendar_today).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent(true);
            }
        });
        v.findViewById(R.id.iv_month_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToPre(true);
            }
        });
        v.findViewById(R.id.iv_month_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToNext(true);
            }
        });
        v.findViewById(R.id.iv_calendar_events).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack != null) {
                    mCallBack.onEventListClick();
                }
            }
        });
        mDateFormat = context.getResources().getString(R.string.calendar_date);
        String showDate = String.format(mDateFormat, mCalendarView.getCurYear(), mCalendarView.getCurMonth());
        mTvYearMonth.setText(showDate);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        v.setLayoutParams(params);
        addView(v);
    }

    private Calendar getSchemeCalendar(int year, int month, int day) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(SCHEME_POINT_COLOR);//如果单独标记颜色、则会使用这个颜色
        return calendar;
    }

    public int getCurrYear() {
        return mCalendarView.getCurYear();
    }

    public int getCurrMonth() {
        return mCalendarView.getCurMonth();
    }

    public int getCurrDay() {
        return mCalendarView.getCurDay();
    }

    public int getCalendarYear() {
        return mCalendarView.getSelectedCalendar().getYear();
    }

    public int getCalendarMonth() {
        return mCalendarView.getSelectedCalendar().getMonth();
    }

    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public void setMonthViewScrollable(boolean scrollable) {
        mCalendarView.setMonthViewScrollable(scrollable);
    }

    public void setOnMonthChangeListener(final CalendarView.OnMonthChangeListener l) {
        mCalendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                String showDate = String.format(mDateFormat, year, month);
                mTvYearMonth.setText(showDate);
                l.onMonthChange(year, month);
            }
        });
    }

    public void setSchemeDate(@Size(min = 1) List<Integer> days) {
        mCalendarView.clearSchemeDate();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();
        Calendar calendar;
        for (Integer day : days) {
            calendar = getSchemeCalendar(year, month, day);
            map.put(calendar.toString(), calendar);
        }

        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
    }

    public interface CallBack {
        void onEventListClick();
    }
}