/*
 *
 *   Copyright(c) 2015, QuCai8, Inc. All rights reserved.
 *   This software is the confidential and proprietary information of QuCai8, Inc.
 *   You shall not disclose such Confidential Information and shall use it only in
 *   accordance with the terms of the license agreement you entered into with QuCai8.
 *
 */

/**
 * By Mark Chen
 *
 * add property mMaxCount
 * add property mBadgeCount
 *
 * When BadgeCount larger than mMaxCount,the Badge shows"..."
 */
package uguess.qucai.com.merchant.business.common.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TabWidget;
import android.widget.TextView;


public class BadgeView extends TextView {

    private boolean mHideOnNull = true;

    private int mMaxCount;

    private int mBadgeCount;

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        if (!(getLayoutParams() instanceof LayoutParams)) {
            LayoutParams layoutParams =
                    new LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.RIGHT | Gravity.TOP);
            setLayoutParams(layoutParams);
        }

        // set default font
        setTextColor(Color.WHITE);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        setPadding(dip2Px(5), dip2Px(1), dip2Px(5), dip2Px(1));

        // set default background
        setBackground(9, Color.parseColor("#d3321b"));

        setGravity(Gravity.CENTER);

        // default values
        setHideOnNull(true);
        setBadgeCount(0);
    }



    @SuppressWarnings("deprecation")
    public void setBackground(int dipRadius, int badgeColor) {
        int radius = dip2Px(dipRadius);
        float[] radiusArray = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };

        RoundRectShape roundRect = new RoundRectShape(radiusArray, null, null);
        ShapeDrawable bgDrawable = new ShapeDrawable(roundRect);
        bgDrawable.getPaint().setColor(badgeColor);
        setBackgroundDrawable(bgDrawable);
    }

    /**
     * @return Returns true if view is hidden on badge value 0 or null;
     */
    public boolean isHideOnNull() {
        return mHideOnNull;
    }

    /**
     * @param hideOnNull the hideOnNull to set
     */
    public void setHideOnNull(boolean hideOnNull) {
        mHideOnNull = hideOnNull;
        setText(String.valueOf(mBadgeCount));
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.TextView#setText(java.lang.CharSequence, android.widget.TextView.BufferType)
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isHideOnNull() && (text == null || text.toString().equalsIgnoreCase("0"))) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
        if(mMaxCount>0&&mBadgeCount>mMaxCount){
            super.setText("...", type);
            return;
        }
        super.setText(text, type);
    }

    public void showBadge(){
        setVisibility(View.VISIBLE);
    }

    public void hideBadge(){
        setVisibility(View.GONE);
    }

    public void setBadgeCount(int count) {
        mBadgeCount = count;
        setText(String.valueOf(mBadgeCount));
    }

    public Integer getBadgeCount() {
        if (mBadgeCount==0 || getText() == null) {
            return 0;
        }

       return mBadgeCount;
    }

    public void setBadgeGravity(int gravity) {
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
    }

    public int getBadgeGravity() {
        LayoutParams params = (LayoutParams) getLayoutParams();
        return params.gravity;
    }

    public void setBadgeMargin(int dipMargin) {
        setBadgeMargin(dipMargin, dipMargin, dipMargin, dipMargin);
    }

    public void setBadgeMargin(int leftDipMargin, int topDipMargin, int rightDipMargin, int bottomDipMargin) {
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.leftMargin = dip2Px(leftDipMargin);
        params.topMargin = dip2Px(topDipMargin);
        params.rightMargin = dip2Px(rightDipMargin);
        params.bottomMargin = dip2Px(bottomDipMargin);
        setLayoutParams(params);
    }

    public int[] getBadgeMargin() {
        LayoutParams params = (LayoutParams) getLayoutParams();
        return new int[] { params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin };
    }

    public void incrementBadgeCount(int increment) {
        Integer count = getBadgeCount();
        if (count == null) {
            setBadgeCount(increment);
        } else {
            setBadgeCount(increment + count);
        }
    }

    public void decrementBadgeCount(int decrement) {
        incrementBadgeCount(-decrement);
    }

    /*
     * Attach the BadgeView to the TabWidget
     * 
     * @param target the TabWidget to attach the BadgeView
     * 
     * @param tabIndex index of the tab
     */
    public void setTargetView(TabWidget target, int tabIndex) {
        View tabView = target.getChildTabViewAt(tabIndex);
        setTargetView(tabView);
    }

    /*
     * Attach the BadgeView to the target view
     * 
     * @param target the view to attach the BadgeView
     */
    public void setTargetView(View target) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
//            this.get
        }

        if (target == null) {
            return;
        }

        if (target.getParent() instanceof FrameLayout) {
            ((FrameLayout) target.getParent()).addView(this);

        } else if (target.getParent() instanceof ViewGroup) {
            // use a new Framelayout container for adding badge
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int groupIndex = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);

            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentlayoutParams = target.getLayoutParams();

            badgeContainer.setLayoutParams(parentlayoutParams);
            target.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parentContainer.addView(badgeContainer, groupIndex, parentlayoutParams);
            badgeContainer.addView(target);

            badgeContainer.addView(this);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        }

    }

    /*
     * converts dip to px
     */
    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    public void setMaxCount(int maxCount) {
        mMaxCount = maxCount;
    }
}
