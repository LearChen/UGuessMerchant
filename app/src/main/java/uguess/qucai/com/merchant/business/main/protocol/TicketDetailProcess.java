package uguess.qucai.com.merchant.business.main.protocol;

import org.json.JSONObject;

import uguess.qucai.com.merchant.business.common.module.Ticket;
import uguess.qucai.com.merchant.framework.protocol.BaseProcess;

import static uguess.qucai.com.merchant.framework.util.JSONUtil.BODY_TAG;
import static uguess.qucai.com.merchant.framework.util.JSONUtil.STATUS_TAG;
import static uguess.qucai.com.merchant.util.Const.ResponseResultCode.RESULT_SUCCESS;

/**
 * Created by MarkChen on 15/10/24.
 */
public class TicketDetailProcess extends BaseProcess{

    private final String url = "/checksold/get_benefit_detail.html";
    private String consumeCode;
    private Ticket resultTicket = new Ticket();

    @Override
    protected String getRequestUrl() {
        return url;
    }

    @Override
    protected String getInfoParameter() {
        JSONObject j = new JSONObject();

        try {
            j.put("consume_code",consumeCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j.toString();
    }

    @Override
    protected void onResult(JSONObject result) {
        try {
            int resultCode = result.optInt(STATUS_TAG);
            setProcessStatus(resultCode);
            if (resultCode == RESULT_SUCCESS) {
                JSONObject body = result.optJSONObject(BODY_TAG);
                resultTicket.setId(body.optString("id"));
                resultTicket.setTicketName(body.optString("name"));
                resultTicket.setStatus(body.optInt("status"));
                resultTicket.setTicketValue(body.optString("value"));
                resultTicket.setExpireTime(body.optString("expire_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected String getFakeResult() {
        return null;
    }

    public String getConsumeCode() {
        return consumeCode;
    }

    public void setConsumeCode(String consumeCode) {
        this.consumeCode = consumeCode;
    }

    public Ticket getResult() {
        return resultTicket;
    }
}
