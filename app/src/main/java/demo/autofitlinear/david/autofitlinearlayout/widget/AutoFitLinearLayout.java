package demo.autofitlinear.david.autofitlinearlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by David on 2016/4/20.
 */
public class AutoFitLinearLayout extends ViewGroup {

    private static final String TAG = "AutoFitLinearLayout";

    private int width = 0;

    public AutoFitLinearLayout(Context context) {
        this(context, null);
    }

    public AutoFitLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setDefaultValue() {
        
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int totalViewCount = getChildCount();
        int visibleViewCount = 0;
        for(int i = 0; i < totalViewCount;i++) {
            if(getChildAt(i).getVisibility() != View.GONE) visibleViewCount++;
        }

        for(int i = 0, j = 0; i < totalViewCount && j < visibleViewCount; i++) {
            View childAt = getChildAt(i);
            if(childAt.getVisibility() != View.GONE) {
                LayoutParams lp = childAt.getLayoutParams();
                childAt.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), lp.width), heightMeasureSpec);
                childAt.getMeasuredHeight();
                j++;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
    }
}
