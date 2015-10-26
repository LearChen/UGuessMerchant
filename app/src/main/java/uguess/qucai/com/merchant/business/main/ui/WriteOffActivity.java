package uguess.qucai.com.merchant.business.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.business.common.component.QCActionBar;
import uguess.qucai.com.merchant.framework.ui.base.BaseActivity;
import uguess.qucai.com.merchant.framework.util.StringUtil;
import uguess.qucai.com.merchant.util.Const;

public class WriteOffActivity extends BaseActivity {

    private ImageView vScan;
    private EditText vEditTicketCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_off);
        vEditTicketCode=(EditText)findViewById(R.id.edit_ticket_code);
        initActionBar();
        findViewById(R.id.btn_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ticketCode = vEditTicketCode.getText().toString();
                if(!StringUtil.isEmpty(ticketCode)){
                    ticketCode = ticketCode.replaceAll(" ","");
                    ticketCode = ticketCode.toUpperCase();
                }
                if(!StringUtil.isEmpty(ticketCode)){
                    Intent i = new Intent(getActivity(),TicketDetailActivity.class);
                    i.putExtra("code",ticketCode);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getActivity(),ZbarScanActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        vEditTicketCode.setText("");
    }

    /**
     * 初始化ActionBar
     */
    private void initActionBar() {
        //初始化actionbar并获取操作按钮
        QCActionBar actionBar = (QCActionBar) findViewById(R.id.action_bar);
        actionBar.setTitle(R.string.title_activity_write_off);
    }
}
