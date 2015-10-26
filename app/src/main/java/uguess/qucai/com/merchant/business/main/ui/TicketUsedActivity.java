package uguess.qucai.com.merchant.business.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.business.common.component.QCActionBar;

public class TicketUsedActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_used);
        initActionBar();
    }

    public void returnToScan(View view){
        finish();
    }
    /**
     * 初始化ActionBar
     */
    private void initActionBar() {
        //初始化actionbar并获取操作按钮
        QCActionBar actionBar = (QCActionBar) findViewById(R.id.action_bar);
        actionBar.setTitle(R.string.title_activity_ticket_used);
    }

}
