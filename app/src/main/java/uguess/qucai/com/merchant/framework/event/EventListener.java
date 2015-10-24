package uguess.qucai.com.merchant.framework.event;

public interface EventListener {
    void onEvent(EventId id, EventArgs args);
}
