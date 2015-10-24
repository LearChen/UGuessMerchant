/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */

package uguess.qucai.com.merchant.framework.ui.base;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.analytics.MobclickAgent;

import uguess.qucai.com.merchant.framework.event.EventId;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.UIEventListener;
import uguess.qucai.com.merchant.framework.ui.component.Loading;
import uguess.qucai.com.merchant.util.Const;

;
//import com.qucai.guess.business.easemob.utils.CommonUtils;


public class BaseFragmentActivity extends SuperActivity {
    private static final int notifiId = 11;
    protected NotificationManager notificationManager;


    // helper
    public BaseFragmentActivity getActivity() {
        return this;
    }

    // flag
    /**
     * activity是否正在销毁
     * 主要用以判断逻辑层回调后是否继续执行
     */
    private boolean mCreated = false;

    public boolean isCreated() {
        return mCreated;
    }

    // event
    private UIEventListener.Helper mEventListenerHelper = new UIEventListener.Helper();

    public UIEventListener createUIEventListener(EventListener listener) {
        return mEventListenerHelper.createUIEventListener(listener);
    }

    public void addUIEventListener(EventId id, EventListener listener) {
        mEventListenerHelper.add(id, listener);
    }

    public void removeUIEventListener(EventListener listener) {
        mEventListenerHelper.remove(listener);
    }

    // loading
    private Loading mLoading = null;

    public void startLoading() {
        if (!getLoading().isShow()) {
            getLoading().start(this);
        }
    }

    public void stopLoading() {
        if(getLoading()!=null){
            getLoading().stop();
        }
    }

    public Loading getLoading() {
        if (mLoading == null) {
            mLoading = new Loading();
        }
        return mLoading;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
//		DebugLogger.write("onAttachFragment " + this);
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mCreated = true;
        mEventListenerHelper.setHost(this);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        mCreated = false;
        mEventListenerHelper.clear();
//		DebugLogger.write("onDestroy " + this);
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//		DebugLogger.write("onNewIntent " + this);
    }

    @Override
    protected void onPause() {
//		DebugLogger.write("onPause " + this);
        if (mLoading != null) {
            mLoading.forceStop();
        }
        MobclickAgent.onPause(this);
        Const.Application.setActivityOnStop(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Const.Application.setCurrentActivity(this);
        Const.Application.setActivityOnStart(this);
        MobclickAgent.onResume(this);
//		DebugLogger.write("onResume " + this);
    }

//	@Override
//	protected void onResumeFragments() {
//		super.onResume();
////		DebugLogger.write("onResumeFragments " + this);
//	}

    @Override
    protected void onStart() {
        super.onStart();
//		DebugLogger.write("onStart " + this);
    }

    @Override
    protected void onStop() {
//		DebugLogger.write("onStop " + this);
        super.onStop();
    }

    public boolean isActyDestroyed() {
        return super.isFinishing();
    }

    /**
     * 当应用在前台时，如果当前消息不是属于当前会话，在状态栏提示一下
     * 如果不需要，注释掉即可
     *
     * @param message
     */
//    protected void notifyNewMessage(BaseMessage message,String nick) {
//        //如果是设置了不提醒只显示数目的群组(这个是app里保存这个数据的，demo里不做判断)
//        //以及设置了setShowNotificationInbackgroup:false(设为false后，后台时sdk也发送广播)
//        if(!EasyUtils.isAppRunningForeground(this)){
//            return;
//        }
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(getApplicationInfo().icon)
//                .setWhen(System.currentTimeMillis()).setAutoCancel(false);
//
//        String ticker ="";//= CommonUtils.getMessageDigest(message, this);
//        String st = getResources().getString(R.string.expression);
//        if(message.getType() == MessageEnums.Type.TXT)
//            ticker = ticker.replaceAll("\\[.{2,3}\\]", st);
//        //设置状态栏提示
//        mBuilder.setTicker(nick+": " + ticker);
//        mBuilder.setContentText("查看新的未读消息");
//        mBuilder.setContentTitle(message.getFromNick());
//        mBuilder.setPriority(5);
//        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//        //必须设置pendingintent，否则在2.3的机器上会有bug
//        Intent intent = new Intent(this, ChatActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        i.putExtra(Const.Intent.FRIEND_SOURCE_KEY, friendSource);
//        if(message.getToImId().equals(Cache.getInstance().getUser().getUserImId())){
//            intent.putExtra(Const.Intent.IM_USER_ID, message.getFromImId());
//            intent.putExtra(Const.Intent.IM_USER_NICK_NAME, message.getFromNick());
//            intent.putExtra(Const.Intent.IM_USER_TO_CHAT_AVATAR, message.getFromAvatar());
//        }else{
//            intent.putExtra(Const.Intent.IM_USER_ID, message.getToImId());
//            intent.putExtra(Const.Intent.IM_USER_NICK_NAME, message.getToNick());
//            intent.putExtra(Const.Intent.IM_USER_TO_CHAT_AVATAR, message.getToAvatar());
//        }
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifiId, intent, PendingIntent.FLAG_ONE_SHOT);
//        mBuilder.setContentIntent(pendingIntent);
//        Notification notification = mBuilder.build();
//        notificationManager.notify(notifiId, notification);
////        notificationManager.cancel(notifiId);
//    }
}
