package uguess.qucai.com.merchant.framework.ui.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.framework.ui.helper.Alert;
import uguess.qucai.com.merchant.framework.util.ActivityController;


/**
 * Created by NO1 on 2015/7/2.
 */
public abstract class SuperActivity extends Activity {

    private IntentFilter mIntentFilter;

    private NetWorkState mNetWorkState;

    private NetworkInfo mNetworkInfo;

    public abstract void startLoading();

    public abstract void stopLoading();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        ActivityController.addActivity(this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetWorkState = new NetWorkState();
        registerReceiver(mNetWorkState, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
        unregisterReceiver(mNetWorkState);
    }

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
}
