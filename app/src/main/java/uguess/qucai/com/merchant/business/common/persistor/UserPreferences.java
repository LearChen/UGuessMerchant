package uguess.qucai.com.merchant.business.common.persistor;

import android.content.Context;
import android.content.SharedPreferences;

import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.util.Const;


public class UserPreferences {

    public void set(User user) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString("lastuid", user.getUserId());
        editor.putString("lastdeviceid", user.getDeviceId());
        editor.putInt("lastmode", user.getLoginMode());
        editor.putString("lastopenid", user.getOpenId());
        editor.putInt("lastgrade", user.getGrade());
        editor.putString("lastpass", user.getPassWord());
        editor.putString("lastuname", user.getUserName());
        editor.putString("lastcellnum", user.getCellNum());
        editor.putString("lasttoken", user.getToken());
        editor.putString("lastportraiturl", user.getPortraitURL());
        editor.putString("lastbirthday", user.getBirthday());
        editor.putInt("lastgender", user.getGender());
        editor.putString("lastimid", user.getUserImId());
        editor.putString("lastnickname", user.getNickName());
        editor.putString("lastAvatarsToken", user.getAvatarsToken());
        editor.putString("lastImagesToken", user.getImagesToken());
        editor.putString("lastDocumentToken", user.getDocumentToken());
        editor.putString("lastInviteCode", user.getInviteCode());
        editor.commit();
    }

    public void setPublicKey(String publicKey) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString("publicKey", publicKey).commit();
    }

    public String getPublicKey() {
        SharedPreferences sp = getPreferences();
        return sp.getString("publicKey", "");
    }

    public User getLastest() {
        SharedPreferences sp = getPreferences();
       User user = new User();
        user.setLoginMode(sp.getInt("lastmode", -1));
        user.setUserId(sp.getString("lastuid", ""));
        user.setDeviceId(sp.getString("lastdeviceid", ""));
        if (user.getLoginMode() > -1) {
            user.setOpenId(sp.getString("lastopenid", ""));
        }
        user.setPassWord(sp.getString("lastpass", ""));
        user.setUserName(sp.getString("lastuname", ""));
        user.setCellNum(sp.getString("lastcellnum", ""));
        user.setToken(sp.getString("lasttoken", ""));
        user.setBirthday(sp.getString("lastbirthday", ""));
        user.setUserImId(sp.getString("lastimid", ""));
        user.setPortraitURL(sp.getString("lastportraiturl", ""));
        user.setGender(sp.getInt("lastgender", 1));
        user.setNickName(sp.getString("lastnickname", ""));
        user.setAvatarsToken(sp.getString("lastAvatarsToken", ""));
        user.setImagesToken(sp.getString("lastImagesToken", ""));
        user.setDocumentToken(sp.getString("lastDocumentToken", ""));
        user.setGrade(sp.getInt("lastgrade", 0));
        user.setInviteCode(sp.getString("lastInviteCode", ""));
        return user;
    }

    public void clearLastestPass() {
        getPreferences().edit().putString("lastpass", "").commit();
    }

    private SharedPreferences getPreferences() {
        return Const.Application.getSharedPreferences(Const.SharedPreferenceKey.UserPreference, Context.MODE_PRIVATE);
    }

    public void setHasPayPwdFlag(){
        getPreferences().edit().putBoolean("hasPayPwd",true);
    }

    public boolean isHasPayPwd(){
        return getPreferences().getBoolean("hasPayPwd",false);
    }
}



