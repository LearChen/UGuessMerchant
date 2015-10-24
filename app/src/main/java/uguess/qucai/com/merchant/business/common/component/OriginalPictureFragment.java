/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */

package uguess.qucai.com.merchant.business.common.component;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.util.ImageManager;


/**
 * OriginalPictureFragment.java是启程APP的图片原图Fragment类。
 *
 * @author 金玉龙
 * @version 1.0 2015/6/3
 * @create 2015/6/3 20:38
 * @modify 2015/6/3 20:38
 */
@SuppressLint("ValidFragment")
public class OriginalPictureFragment extends Fragment {
    private int all;

    private int current;

    /**
     * 获取原图fragment实例的静态方法
     */
    public static OriginalPictureFragment newInstance(String imageUrl) {
        final OriginalPictureFragment f = new OriginalPictureFragment();
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_original_picture, null);
        TextView currentProgress=(TextView) view.findViewById(R.id.current_progress);
        ImageView pic = (ImageView) view.findViewById(R.id.image);
        String url = getArguments().getString("url");
        ImageManager.displayImageDefault(url, pic);
        currentProgress.setText(new StringBuilder().append(current).append("/").append(all).toString());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
