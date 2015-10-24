/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */

package uguess.qucai.com.merchant.business.common.component;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import uguess.qucai.com.merchant.R;


/**
 * Created by NO3 on 2015/5/5.
 */
public class QCActionBar extends LinearLayout {
    /**
     * 布局文件的inflater
     */
    private LayoutInflater inflater;
    /**
     * 图标默认的padding
     */
    private static final int PADDING = 10;

    /**
     * 图标默认的margin
     */
    private static final int MARGIN = 70;

    /**
     * 图标区，标题区，操作区
     */
    private LinearLayout rootLinear, iconLinear, titleLinear, operatorLinear;
    /**
     * 系统状态栏的
     */
    private LinearLayout systemLayout,notifySystemLayout;
    /**
     * 标题的TextView
     */
    private TextView title;
    /**
     * 用户存储操作区item的Map
     */
    private Map<String, Integer> operatorMap = new HashMap<String, Integer>();

    /**
     * 构造器
     *
     * @param context
     */
    public QCActionBar(Context context) {
        super(context);
    }


    /**
     * 构造器初始化
     *
     * @param context
     * @param attributeSet
     */
    public QCActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_qucai_actionbar, this);
        rootLinear = (LinearLayout) findViewById(R.id.action_bar_root);
        iconLinear = (LinearLayout) findViewById(R.id.iconLinear);
        titleLinear = (LinearLayout) findViewById(R.id.titleLinear);
        operatorLinear = (LinearLayout) findViewById(R.id.operatorLinear);
        title = (TextView) findViewById(R.id.title);
        systemLayout=(LinearLayout)findViewById(R.id.system_bar);
        notifySystemLayout=(LinearLayout)findViewById(R.id.notify_system_bar);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            systemLayout.setVisibility(GONE);
            notifySystemLayout.setVisibility(GONE);
        }
    }

    /**
     * 设置actionbar的背景
     * @param resId
     */
    public void setBackground(int resId){
        rootLinear.setBackgroundColor(resId);
        systemLayout.setBackgroundColor(resId);
    }


    /**
     * 设置ActionBar的高度
     *
     * @param height
     */
    public void setHeight(int height) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(height));
        this.setLayoutParams(layoutParams);
        this.setVerticalGravity(Gravity.CENTER);
    }

    /**
     * 添加Icon图片
     *
     * @param resId
     */
    public void addIcon(int resId) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(resId);
        //设置图片布局
        imageView.setLayoutParams(new LayoutParams(dpToPx(45), dpToPx(45)));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
        //添加进icon区域
        iconLinear.addView(imageView);
    }

    /**
     * 通过资源id设置标题
     *
     * @param resId
     */
    public Object setTitle(int resId) {
        title.setText(getResources().getString(resId));
        return title;
    }

    /**
     * 设置标题大小
     *
     * @param size
     */
    public void setTitleSize(float size) {
        title.setTextSize(size);
    }


    /**
     * 设置标题
     *
     * @param titleText
     */
    public Object setTitle(String titleText) {
        title.setText(titleText);
        return title;
    }

    /**
     * 添加副标题
     *
     * @param titleText
     * @return
     */
    public Object setSubTitle(String titleText) {
        TextView textView = new TextView(getContext());
        textView.setText(titleText);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(MARGIN, 0, 0, 0);
        textView.setLayoutParams(lp);
     //   textView.setTextAppearance(this.getContext(), user_max_text_18sp);
        titleLinear.addView(textView);
        return textView;
    }

    /**
     * 添加操作区item
     *
     * @param resId
     */
    public Object addOperator(int resId) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(resId);
        String tag = UUID.randomUUID().toString();
        imageView.setTag(tag);
        if (operatorMap.get(tag) == null) {
            operatorMap.put(tag, resId);
        }
        //设置图片padding
        imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
        //设置item的margin
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(MARGIN, 0, MARGIN, 0);
        imageView.setLayoutParams(lp);
        //添加到布局中
        operatorLinear.addView(imageView, 0);
        return imageView;
    }

    /**
     * 添加操作区item
     *
     * @param textView
     */
    public Object addOperator(TextView textView) {
        operatorLinear.removeAllViews();
        //添加到布局中
        operatorLinear.addView(textView);
        return textView;
    }

    /**
     * 添加操作区item
     *
     * @param resId
     */
    public Object addOperator(int resId, int width, int height) {
        ImageView imageView = (ImageView) addOperator(resId);
        LayoutParams lp = new LayoutParams(dpToPx(width), dpToPx(height));
        lp.setMargins(MARGIN, 0, MARGIN, 0);
        imageView.setLayoutParams(lp);
        return imageView;
    }

    /**
     * @param layoutId
     * @return
     */
    public Object inflateView(int layoutId) {
        View view = inflater.inflate(layoutId, null);
        iconLinear.addView(view, 0);
        return view;
    }


    /**
     * 显示系统状态栏
     */
    public void showSystemBar() {

        systemLayout.setVisibility(GONE);
        notifySystemLayout.setVisibility(GONE);
    }


    /**
     * 将dp值转换为px值
     *
     * @param dp
     * @return px值
     */
    private int dpToPx(int dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }


}
