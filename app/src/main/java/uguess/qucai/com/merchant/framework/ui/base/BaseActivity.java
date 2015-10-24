package uguess.qucai.com.merchant.framework.ui.base;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.framework.event.EventId;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.UIEventListener;
import uguess.qucai.com.merchant.framework.ui.component.Loading;
import uguess.qucai.com.merchant.framework.ui.helper.Alert;
import uguess.qucai.com.merchant.util.Const;

//import com.QuCai.business.ui.chat.utils.CommonUtils;


public class BaseActivity extends SuperActivity {
    private static final int notifiId = 11;
    protected NotificationManager notificationManager;


    // helper
    public BaseActivity getActivity() {
        return this;
    }
    private IntentFilter mIntentFilter;
    private NetWorkState mNetWorkState;

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
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetWorkState = new NetWorkState();
        registerReceiver(mNetWorkState, mIntentFilter);
        //PushAgent.getInstance(getActivity()).onAppStart();


    }

    @Override
    protected void onDestroy() {
        mCreated = false;
        mEventListenerHelper.clear();
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
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        Const.Application.setActivityOnStop(this);
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Const.Application.setCurrentActivity(this);
        Const.Application.setActivityOnStart(this);
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
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
     */
//    protected void notifyNewMessage(BaseMessage message) {
//        //如果是设置了不提醒只显示数目的群组(这个是app里保存这个数据的，demo里不做判断)
//        //以及设置了setShowNotificationInbackgroup:false(设为false后，后台时sdk也发送广播)
//        if(!EasyUtils.isAppRunningForeground(this)){
//            return;
//        }

//        NotificationCompat.Builder mBuilder = mMessageNotificationHelper.getNotificationBuilder(message,this);
//
//        //必须设置pendingintent，否则在2.3的机器上会有bug
//        Intent intent = new Intent(this, ChatActivity.class);
//        if(message.getToImId().equals(Cache.getInstance().getUser().getUserImId())){
//            intent.putExtra(Const.Intent.IM_USER_ID, message.getFromImId());
//            intent.putExtra(Const.Intent.IM_USER_NICK_NAME, message.getFromNick());
//            intent.putExtra(Const.Intent.IM_USER_TO_CHAT_AVATAR, message.getFromAvatar());
//        }else{
//            intent.putExtra(Const.Intent.IM_USER_ID, message.getToImId());
//            intent.putExtra(Const.Intent.IM_USER_NICK_NAME, message.getToNick());
//            intent.putExtra(Const.Intent.IM_USER_TO_CHAT_AVATAR, message.getToAvatar());
//        }
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        intent.putExtra(Const.Intent.IM_NTF_TO_MAIN,true);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifiId, intent, PendingIntent.FLAG_ONE_SHOT);
//        mBuilder.setPriority(5);
//        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//        mBuilder.setContentIntent(pendingIntent);
//        Notification notification = mBuilder.build();
//        notificationManager.notify(notifiId, notification);
////        notificationManager.cancel(notifiId);
//    }

    private class NetWorkState extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo == null || !mNetworkInfo.isAvailable()) {
                Alert.Toast(getString(R.string.network_is_unavailable));
            }
        }
    }

    public NetworkInfo getmNetworkInfo() {
        return mNetworkInfo;
    }

    private NetworkInfo mNetworkInfo;
}
