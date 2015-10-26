package uguess.qucai.com.merchant.business.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.business.common.logic.LogicFactory;
import uguess.qucai.com.merchant.business.user.logic.UserLogic;
import uguess.qucai.com.merchant.business.user.logic.event.UserEventArgs;
import uguess.qucai.com.merchant.business.user.ui.LoginActivity;
import uguess.qucai.com.merchant.framework.event.EventArgs;
import uguess.qucai.com.merchant.framework.event.EventId;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.ui.base.BaseActivity;
import uguess.qucai.com.merchant.framework.util.StringUtil;

public class WelcomeActivity extends BaseActivity {

    private UserLogic logic;

    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnEnter = (Button)findViewById(R.id.btn_enter);
        btnEnter.setVisibility(View.GONE);
        logic = (UserLogic)LogicFactory.self().get(LogicFactory.Type.User);
        logic.loginWithCache(createUIEventListener(new EventListener() {
            @Override
            public void onEvent(EventId id, EventArgs args) {
                btnEnter.setVisibility(View.VISIBLE);
            }
        }));
    }

    public void enter(View view){
        if(!StringUtil.isEmpty(Cache.getInstance().getUser().getUserId())){
            startActivity(new Intent(getActivity(),WriteOffActivity.class));
            finish();
        }else{
            startActivity(new Intent(getActivity(), LoginActivity.class));
            finish();
        }

    }

}
