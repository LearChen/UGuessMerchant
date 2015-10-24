package uguess.qucai.com.merchant.business.main.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.framework.ui.base.BaseActivity;

public class TicketDetailActivity extends BaseActivity {

    private TextView vTicketCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        vTicketCode= (TextView)findViewById(R.id.textview_ticket_code);
        vTicketCode.setText(getIntent().getStringExtra("code"));
    }

    public void useTicket(View view){

    }


}
