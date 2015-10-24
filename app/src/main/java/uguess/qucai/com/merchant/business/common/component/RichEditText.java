/*
 *
 *  *
 *  *   Copyright(c) 2015, QuCai8, Inc. All rights reserved.
 *  *   This software is the confidential and proprietary information of QuCai8, Inc.
 *  *   You shall not disclose such Confidential Information and shall use it only in
 *  *   accordance with the terms of the license agreement you entered into with QuCai8.
 *  *
 *
 *
 */

package uguess.qucai.com.merchant.business.common.component;

import android.content.Context;
import android.text.Layout;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class RichEditText extends EditText {
    private long first_click = 0;
    private long second_click = 0;

    public RichEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public RichEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichEditText(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean _boolean = super.onTouchEvent(event);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= getTotalPaddingLeft();
        y -= getTotalPaddingTop();

        x += getScrollX();
        y += getScrollY();

        Layout layout = getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);
        setSelection(off);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            /**
             * get you interest span
             */
            first_click = System.currentTimeMillis();
            if (isDoubleClick()) {
                ImageSpan[] link = getText().getSpans(off, off, ImageSpan.class);
                if (link.length != 0) {
//    				String src = link[0].getSource();
//    				Intent intent = new Intent(getContext(), SamplePhotoActivity.class);
//    				intent.putExtra(SamplePhotoActivity.SAMPLE_PHOTO_PATH, src);
//    				intent.putExtra(SamplePhotoActivity.SAMPLE_PHOTO_ENCRYPTION, false);
//    				intent.putExtra(SamplePhotoActivity.BASE_PHOTO_PATH, TPCConfiguration.getTPCSDCardRoot());
//    				getContext().startActivity(intent);
                    return true;
                }
            }
            second_click = first_click;
        }

        return _boolean;
    }

    private boolean isDoubleClick() {
        return first_click - second_click < 600;
    }
}
