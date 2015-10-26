package uguess.qucai.com.merchant.business.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uguess.qucai.com.merchant.R;
import uguess.qucai.com.merchant.business.common.logic.LogicFactory;
import uguess.qucai.com.merchant.business.common.module.Ticket;
import uguess.qucai.com.merchant.business.main.logic.TicketLogic;
import uguess.qucai.com.merchant.business.main.logic.event.TicketEventArgs;
import uguess.qucai.com.merchant.framework.event.EventArgs;
import uguess.qucai.com.merchant.framework.event.EventId;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.ui.base.BaseActivity;
import uguess.qucai.com.merchant.framework.ui.helper.Alert;

public class TicketDetailActivity extends BaseActivity {

    private TextView vTicketCode;
    private TicketLogic logic;

    private TextView vTicketName;
    private TextView vTicketValue;
    private TextView vTicketDeadline;
    private TextView vTicketStatus;
    private Button vUseTicket;

    private String ticketId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        vTicketCode= (TextView)findViewById(R.id.textview_ticket_code);
        vTicketName = (TextView)findViewById(R.id.tv_ticket_name);
        vTicketValue = (TextView)findViewById(R.id.tv_ticket_content);
        vTicketDeadline = (TextView)findViewById(R.id.tv_ticket_deadline);
        vTicketStatus = (TextView)findViewById(R.id.tv_ticket_status);
        vUseTicket = (Button)findViewById(R.id.btn_use_ticket);

        String codeParam = getIntent().getStringExtra("code");
        StringBuffer viewCode = new StringBuffer();
        for(int i=0;i<codeParam.length();i+=4){
            if(i+4<codeParam.length()){
                viewCode.append(codeParam.substring(i,i+4));
                viewCode.append(" ");
            }else {
                viewCode.append(codeParam.substring(i));
            }
        }
        vTicketCode.setText(viewCode.toString());
        logic = (TicketLogic)LogicFactory.self().get(LogicFactory.Type.Ticket);
        logic.getTicketDetail(codeParam, createUIEventListener(new EventListener() {
            @Override
            public void onEvent(EventId id, EventArgs args) {
                TicketEventArgs result = (TicketEventArgs) args;
                OperErrorCode errCode = result.getErrCode();
                vUseTicket.setClickable(false);
                switch (errCode) {
                    case Success:
                        setValue(result.getResult());
                        vUseTicket.setClickable(true);
                        break;
                    case TicketNotExist:
                        setValue(result.getResult());
                        vUseTicket.setText(R.string.ticket_not_exist);
//                        Alert.Toast(R.string.ticket_not_exist);
                        break;
                    case TicketExpired:
                        setValue(result.getResult());
                        vUseTicket.setText(R.string.text_status_expired);
//                        Alert.Toast(R.string.text_status_expired);
                        break;
                    case TicketAlreadyUsed:
                        setValue(result.getResult());
                        vUseTicket.setText(R.string.text_status_used);
//                        Alert.Toast(R.string.text_status_used);
                        break;
                    case TicketNoPermision:
                        setValue(result.getResult());
                        vUseTicket.setText(R.string.ticket_no_permision);
//                        Alert.Toast(R.string.ticket_no_permision);
                        break;
                    default:
                        setValue(result.getResult());
                        vUseTicket.setText(R.string.ticket_no_permision);
                        vTicketStatus.setText(R.string.warning_invalid);
//                        Alert.Toast(R.string.ticket_no_permision);
                        break;
                }
            }
        }));
    }

    private void setValue(Ticket result){
        vTicketName.setText(result.getTicketName());
        vTicketDeadline.setText(result.getExpireTime());
        switch (result.getStatus()){
            case 0:
                vTicketStatus.setText(R.string.text_status_useful);
                break;
            case 1:
                vTicketStatus.setText(R.string.text_status_used);
                break;
            case 2:
                vTicketStatus.setText(R.string.text_status_expired);
                break;
            default:
                vTicketStatus.setText(R.string.warning_invalid);
        }
        vTicketValue.setText(result.getTicketValue());
        ticketId = result.getId();
    }

    public void useTicket(View view){
        logic.useTicket(ticketId,createUIEventListener(new EventListener() {
            @Override
            public void onEvent(EventId id, EventArgs args) {

                stopLoading();
                TicketEventArgs result = (TicketEventArgs) args;
                OperErrorCode errCode = result.getErrCode();
                switch (errCode) {
                    case Success:
                        startActivity(new Intent(getActivity(),TicketUsedActivity.class));
                        finish();
                        break;
                    case TicketNotExist:
                        vUseTicket.setText(R.string.ticket_not_exist);
                        Alert.Toast(R.string.ticket_not_exist);
                        break;
                    case TicketExpired:
                        vUseTicket.setText(R.string.text_status_expired);
                        Alert.Toast(R.string.text_status_expired);
                        break;
                    case TicketAlreadyUsed:
                        vUseTicket.setText(R.string.text_status_used);
                        Alert.Toast(R.string.text_status_used);
                        break;
                    case TicketNoPermision:
                        vUseTicket.setText(R.string.ticket_no_permision);
                        Alert.Toast(R.string.ticket_no_permision);
                        break;
                    default:
                        vUseTicket.setText(R.string.ticket_no_permision);
                        Alert.Toast(R.string.ticket_no_permision);
                        break;
                }
            }
        }));
        startLoading();
    }


}
