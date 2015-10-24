package uguess.qucai.com.merchant.business.main.logic;

import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.business.common.protocol.ProcessStatus;
import uguess.qucai.com.merchant.business.main.logic.event.TicketEventArgs;
import uguess.qucai.com.merchant.business.main.protocol.TicketDetailProcess;
import uguess.qucai.com.merchant.business.user.logic.event.UserEventArgs;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.logic.BaseLogic;
import uguess.qucai.com.merchant.framework.protocol.ResponseListener;
import uguess.qucai.com.merchant.framework.util.Logger;

/**
 * Created by MarkChen on 15/10/24.
 */
public class TicketLogic extends BaseLogic {

    private static final Logger logger = new Logger("TicketLogic");

    /**
     * 工厂方法产生竞猜逻辑类
     */
    public static class Factory implements BaseLogic.Factory {
        @Override
        public BaseLogic create() {
            return new TicketLogic();
        }
    }

    public void getTicketDetail(String ticketCode,final EventListener listener){
        final TicketDetailProcess process = new TicketDetailProcess();
        process.setConsumeCode(ticketCode);
        process.run(new ResponseListener() {
            @Override
            public void onResponse(String requestId) {
                // 状态转换：从调用结果状态转为操作结果状态
                OperErrorCode errCode = ProcessStatus.convertFromStatus(process.getStatus());
                logger.d("login process response, " + errCode);
                TicketEventArgs ticketEventArgs = new TicketEventArgs(process.getResult(), errCode);
                if (errCode == OperErrorCode.Success) {
                    Cache.getInstance().setLoginFlag(true);
                }
                //发送事件
                fireEvent(listener, ticketEventArgs);
            }
        });

    }

}
