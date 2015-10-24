package uguess.qucai.com.merchant.business.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import uguess.qucai.com.merchant.QucaiApplication;
import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.business.common.logic.LogicFactory;
import uguess.qucai.com.merchant.business.main.ui.WriteOffActivity;
import uguess.qucai.com.merchant.business.user.logic.UserLogic;
import uguess.qucai.com.merchant.business.user.logic.event.UserEventArgs;
import uguess.qucai.com.merchant.framework.event.EventArgs;
import uguess.qucai.com.merchant.framework.event.EventId;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.ui.base.BaseActivity;
import uguess.qucai.com.merchant.framework.ui.helper.Alert;
import uguess.qucai.com.merchant.framework.util.StringUtil;

public class LoginActivity extends BaseActivity{

    /**
     * 用户帐号
     */
    private EditText mUserAccount;
    /**
     * 用户密码
     */
    private EditText mUserPassword;

    private UserLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserAccount = (EditText) findViewById(R.id.edit_user_name);
        mUserPassword = (EditText) findViewById(R.id.edit_pwd);
        logic = (UserLogic) LogicFactory.self().get(LogicFactory.Type.User);
    }

    public void login(View view){
        /**
         * 用户初次登陆
         */
        boolean isFirstLogin = true;
        if(StringUtil.isEmpty(mUserAccount.getText().toString())){
            Alert.Toast(R.string.warning_empty_account);
            mUserAccount.requestFocus();
            return;
        }
        if(StringUtil.isEmpty(mUserPassword.getText().toString())){
            Alert.Toast(R.string.warning_empty_pwd);
            mUserPassword.requestFocus();
            return;
        }
        if (getmNetworkInfo() != null && getmNetworkInfo().isAvailable()) {
            logic.login(mUserAccount.getText().toString(),mUserPassword.getText().toString(),isFirstLogin,createUIEventListener(new EventListener() {
                @Override
                public void onEvent(EventId id, EventArgs args) {
                    stopLoading();
                    UserEventArgs result = (UserEventArgs) args;
                    OperErrorCode errCode = result.getErrCode();
                    switch (errCode) {
                        case Success:
                            QucaiApplication.getInstance().setFirstLogin(mUserAccount.getText().toString());
                            Intent intent = new Intent(LoginActivity.this, WriteOffActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case UidNoExist:
                            Alert.Toast(getString(R.string.tip_user_name_not_exist));
                            break;
                        case UidInvalid:
                            Alert.Toast(getString(R.string.tip_user_name_not_exist));
                            break;
                        case PasswordError:
                            Alert.Toast(getString(R.string.tip_user_password_wrong));
                            break;
                        case NetNotAviable:
                            Alert.showNetAvaiable();
                            break;
                        default:
                            Alert.Toast(getString(R.string.tip_user_login_fail));
                            break;
                }
            }}));
        } else {
            Alert.Toast(getString(R.string.network_is_unavailable));
        }
        startLoading();

    }


}
