/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */

package uguess.qucai.com.merchant.business.common.component.photo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import uguess.qucai.com.merchant.business.common.component.OriginalPictureFragment;

/**
 * PicViewPagerAdapter.java是启程APP的用户相册的Adapter类。
 *
 * @author 金玉龙
 * @version 1.0 2015/6/3
 * @create 2015/6/3 20:38
 * @modify 2015/6/3 20:38
 */
public class PicViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> photos;

    public PicViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public PicViewPagerAdapter(FragmentManager fm, List<String> photos) {
        super(fm);
        this.photos = photos;
    }

    @Override
    public Fragment getItem(int i) {
        String url = photos.get(i);
        OriginalPictureFragment originalPictureFragment =  OriginalPictureFragment.newInstance(url);
        originalPictureFragment.setAll(photos.size());
        originalPictureFragment.setCurrent(i+1);
        return originalPictureFragment;
    }

    @Override
    public int getCount() {
        return photos.size();
    }


}
