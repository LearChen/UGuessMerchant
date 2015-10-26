package uguess.qucai.com.merchant;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.business.user.protocol.GetPublicKeyProcess;
import uguess.qucai.com.merchant.business.user.protocol.LoginProcess;
import uguess.qucai.com.merchant.framework.net.HttpComm;
import uguess.qucai.com.merchant.framework.net.HttpResultCallback;
import uguess.qucai.com.merchant.framework.util.Logger;
import uguess.qucai.com.merchant.util.Const;
import uguess.qucai.com.merchant.util.CrashHandler;

public class QucaiApplication extends Application {

    private static final Logger logger = new Logger("QucaiApplication");
    /**
     * 上下文
     */
    public static Context applicationContext;

    /**
     * 趣猜应用的实例
     */
    private static QucaiApplication instance;
    /**
     * 即将显示的Activity
     */
    private Activity mShowingActivity = null;
    /**
     * 当前的Activity
     */
    private Activity mCurrentActivity = null;

    public String tradeNo;

    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.openActivityDurationTrack(false);
        Const.Application = this;
        //初始化内存缓存
        Cache.getInstance().onCreate();
        applicationContext = this;
        instance = this;
        /**
         * 获取公钥
         */
        if (TextUtils.isEmpty(Cache.getInstance().getPublicKey())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    fetchPublicKey();
                }
            }).start();
        }
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiscCache(new File(Const.WorkDir + "img/")))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheFileCount(100)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        //Initialize ImageLoader with configuration
        ImageLoader.getInstance().init(config);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(instance);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
        crashHandler.sendPreviousReportsToServer();

    }

    /**
     * 获得上下文
     *
     * @return
     */
    public Context getContext() {
        return applicationContext;
    }

    /**
     * 获取当前的Activity
     *
     * @return 当前的Activity
     */
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 设置当前的Activity
     *
     * @param value 当前的Activity
     */
    public void setCurrentActivity(Activity value) {
        mCurrentActivity = value;
    }

    public boolean isAllActivityHide() {
        return mShowingActivity == null;
    }

    public void setActivityOnStop(Activity activity) {
        // 一般是先新activity onstart，再老activity onstop
        if (activity == mShowingActivity) {
            mShowingActivity = null;
        }
    }

    public void setActivityOnStart(Activity activity) {
        mShowingActivity = activity;
    }

    public static QucaiApplication getInstance() {
        return instance;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        logger.i("enter onTerminate method");
    }


    public JSONObject reLoginAndRepeat(final String url, final String parameter) throws JSONException {
        fetchPublicKey();
        reLogin();
        return rePost(url, parameter);
    }

    public void reLoginAndRepeat() {
        fetchPublicKey();
        reLogin();
    }

    /**
     * 提供外部获取公钥方法
     */
    public void getPublicKey() {
        fetchPublicKey();
    }

    /**
     * 用户是否在设备上为初次登录
     *
     * @param account
     * @return
     */
    public boolean getFirstLogin(String account) {
        boolean isFirstLogin;
        final SharedPreferences pref = getSharedPreferences("userinfo_normal_" + account, Context.MODE_PRIVATE);
        isFirstLogin = pref.getBoolean("isFirstLogin", true);
        return isFirstLogin;
    }

    /**
     * 设置用户不是首次在设备上登录系统
     *
     * @param account
     */
    public void setFirstLogin(String account) {
        final SharedPreferences pref = getSharedPreferences("userinfo_normal_" + account, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstLogin", false);
        editor.commit();
    }

    public void initFirstLogin(String account) {
        final SharedPreferences pref = getSharedPreferences("userinfo_normal_" + account, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstLogin", true);
        editor.commit();
    }

    /**
     * 重新登录
     */
    public void reLogin() {
        User user = Cache.getInstance().getUser();
        if (!TextUtils.isEmpty(user.getPassWord())) {
            final LoginProcess process = getProcessForCacheLogin();
            HttpComm comm = new HttpComm(false);
            comm.post(Const.BASE_URL + process.getRequestUrl(), process.getInfoParameter(), new HttpResultCallback() {

                @Override
                public void onResponse(HttpResultCallback.HttpDownloaderResult success, String url,
                                       String message) {

                    if (success == HttpDownloaderResult.eSuccessful) {
                        try {
                            JSONObject o = new JSONObject(message);
                            callbackForCacheLogin(process.getUserFromResult(o));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onProgress(String url, float rate) {
                }
            });
        }
    }

    private LoginProcess getProcessForCacheLogin() {
        User cachedUser = Cache.getInstance().getUser();
        //登录所用的数据
        User user = new User(cachedUser.getUserName(), cachedUser.getPassWord());
        //登录后台交互过程
        LoginProcess process = new LoginProcess();
        process.setParamUser(user);
        return process;
    }

    /**
     * 重新登录成功后，刷新缓存
     *
     * @param resultUser
     */
    private void callbackForCacheLogin(User resultUser) {
        User user = Cache.getInstance().getUser();
        user.setPassWord(resultUser.getPassWord());
        user.setDeviceId(resultUser.getDeviceId());
        user.setLoginMode(resultUser.getLoginMode());
        if (user.getLoginMode() > -1) {
            user.setOpenId(resultUser.getOpenId());
        }
        user.setToken(resultUser.getToken());
        user.setNickName(resultUser.getNickName());
        user.setPortraitURL(resultUser.getPortraitURL());
        user.setUserImId(resultUser.getUserImId());
        user.setBirthday(resultUser.getBirthday());
        user.setGender(resultUser.getGender());
        user.setAvatarsToken(resultUser.getAvatarsToken());
        user.setImagesToken(resultUser.getImagesToken());
        user.setGrade(resultUser.getGrade());
        user.setPayPwdSetted(resultUser.isPayPwdSetted());
        Cache.getInstance().refreshCacheUser();
    }

    /**
     * 获取公钥并存储
     */
    private void fetchPublicKey() {
        final GetPublicKeyProcess process = new GetPublicKeyProcess();
        HttpComm comm = new HttpComm(false);
        comm.post(Const.BASE_URL + process.getRequestUrl(), process.getInfoParameter(), new HttpResultCallback() {

            @Override
            public void onResponse(HttpDownloaderResult success, String url, String message) {
                if (success == HttpDownloaderResult.eSuccessful) {
                    try {
                        JSONObject o = new JSONObject(message);
                        process.storePublicKey(o);
                    } catch (JSONException e) {
                    }
                } else {
                }
            }

            @Override
            public void onProgress(String url, float rate) {
            }
        });
    }

    private JSONObject rePost(final String url, final String parameter) throws JSONException {
        HttpComm comm = new HttpComm(false);
        return new JSONObject(comm.post(url, parameter));
    }

}
