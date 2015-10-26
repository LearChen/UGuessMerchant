package uguess.qucai.com.merchant.business.main.protocol;

import org.json.JSONObject;

import uguess.qucai.com.merchant.framework.protocol.BaseProcess;

import static uguess.qucai.com.merchant.framework.util.JSONUtil.BODY_TAG;
import static uguess.qucai.com.merchant.framework.util.JSONUtil.STATUS_TAG;
import static uguess.qucai.com.merchant.util.Const.ResponseResultCode.RESULT_SUCCESS;

/**
 * Created by MarkChen on 15/10/26.
 */
public class TicketUseProcess extends BaseProcess {

    private final String url = "/checksold/use.html";
    private String ticketId;

    @Override
    protected String getRequestUrl() {
        return url;
    }

    @Override
    protected String getInfoParameter() {
        JSONObject j = new JSONObject();

        try {
            j.put("id",ticketId);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getFakeResult() {
        return null;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
