package com.ando.views.recycler_hor_date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ando.views.R;

/**
 * Title: MyItemDecoration
 * <p>
 * Description:
 * </p>
 * Author Changbao
 * Date 2018/10/22  14:26
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Paint mPaint;
    private int dividerHeight;


    public MyItemDecoration(Context context) {
        this.mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.LTGRAY);
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.size_2);
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        parent.setBackgroundColor(Color.WHITE);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            float childHeight = child.getHeight();
            float dividerLeft = parent.getPaddingLeft();
            float dividerTop = child.getTop() + childHeight;
            float dividerRight = parent.getWidth() - parent.getPaddingRight();
            float dividerBottom = child.getBottom() + childHeight + dividerHeight;
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, mPaint);
        }
    }

}
