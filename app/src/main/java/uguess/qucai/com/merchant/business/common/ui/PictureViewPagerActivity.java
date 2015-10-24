/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */

package uguess.qucai.com.merchant.business.common.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.business.common.component.CustomViewPager;
import uguess.qucai.com.merchant.business.common.component.QCActionBar;
import uguess.qucai.com.merchant.business.common.component.photo.PicViewPagerAdapter;
import uguess.qucai.com.merchant.util.Const;


/**
 * PictureViewPagerActivity.java是启程APP的图片viewpager类。
 *
 * @author 金玉龙
 * @version 1.0 2015/8/11
 */
public class PictureViewPagerActivity extends FragmentActivity {
    /*
     * 相册的ViewPager
     */
    private CustomViewPager mViewPager;
    /*
     * 进度条
     */
    private ProgressBar progressBar;
    /*
     * ViewPager的适配器
     */
    private PicViewPagerAdapter mAdapter;

    /*
     * 初始相册的List    */
    private List<String> photos;
    /*
     * 相册添加List
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picture_view_pager);
        //从bundle中获取响应数据，包括照片列表，用户id，照片在列表中的位置
        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt(Const.Intent.ALBUM_ITEM_INDEX_KEY);
        photos = (ArrayList<String>) bundle.get(Const.Intent.ALBUM_LIST_KEY);
        //初始化viewpager
        mViewPager = (CustomViewPager) findViewById(R.id.id_pager);
        mAdapter = new PicViewPagerAdapter(getSupportFragmentManager(), photos);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(index);
        initActionBar();
    }


    /**
     * 初始化ActionBar
     */
    private void initActionBar() {
        //初始化actionbar并获取操作按钮
        QCActionBar actionBar = (QCActionBar) findViewById(R.id.action_bar);
        ImageView back = (ImageView) actionBar.findViewById(R.id.bar_left);
        LinearLayout iconLayout = (LinearLayout) actionBar.findViewById(R.id.iconLinear);
        LinearLayout operationLayout = (LinearLayout) actionBar.findViewById(R.id.operatorLinear);
        back.setImageResource(R.drawable.ic_common_back);
        actionBar.setTitle(null);
        actionBar.setBackground(getResources().getColor(R.color.transparent));
        iconLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        actionBar.showSystemBar();
        //点击回退按钮，回退
        iconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        operationLayout.setVisibility(View.INVISIBLE);
    }

}
