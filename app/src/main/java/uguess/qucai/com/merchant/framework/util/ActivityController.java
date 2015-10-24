/*
 *
 *   Copyright(c) 2015, QuCai8, Inc. All rights reserved.
 *   This software is the confidential and proprietary information of QuCai8, Inc.
 *   You shall not disclose such Confidential Information and shall use it only in
 *   accordance with the terms of the license agreement you entered into with QuCai8.
 *
 */

package uguess.qucai.com.merchant.framework.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityController.java是趣猜App的所有Activity的控制类。
 *
 * @author 金玉龙
 * @version 1.0 2015/7/10
 */
public class ActivityController {
    /**
     * 应用中现存的所有activity
     */
    public static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 添加Activity到活动的Activity列表
     *
     * @param activity 开启的activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 从列表中移除Activity
     *
     * @param activity 当前的activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 结束所有的Activity
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
