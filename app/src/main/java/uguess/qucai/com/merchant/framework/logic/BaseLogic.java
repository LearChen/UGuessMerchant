package uguess.qucai.com.merchant.framework.logic;

import android.os.Handler;

import uguess.qucai.com.merchant.framework.event.EventArgs;
import uguess.qucai.com.merchant.framework.event.EventCenter;
import uguess.qucai.com.merchant.framework.event.EventId;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.event.StatusEventArgs;


public class BaseLogic {

    public interface Factory {
        BaseLogic create();
    }

    protected Handler mHandler = new Handler();

    protected void fireStatusEvent(EventId eventId) {
        fireEvent(eventId, new StatusEventArgs(OperErrorCode.Success));
    }

    protected void fireStatusEvent(EventId eventId, OperErrorCode errCode) {
        fireEvent(eventId, new StatusEventArgs(errCode));
    }

    protected void fireEvent(final EventId eventId, final EventArgs args) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                EventCenter.self().fireEvent(eventId, args);
            }
        });
    }

    protected void fireStatusEvent(EventListener listener) {
        fireEvent(listener, new StatusEventArgs(OperErrorCode.Success));
    }

    protected void fireStatusEvent(EventListener listener, OperErrorCode errCode) {
        fireEvent(listener, new StatusEventArgs(errCode));
    }

    protected void fireEvent(final EventListener listener, final EventArgs args) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                listener.onEvent(EventId.eNone, args);
            }
        });
    }
}
