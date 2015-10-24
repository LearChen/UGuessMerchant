package uguess.qucai.com.merchant.business.common.logic;


import java.util.HashMap;
import java.util.Map;

import uguess.qucai.com.merchant.business.main.logic.TicketLogic;
import uguess.qucai.com.merchant.business.user.logic.UserLogic;
import uguess.qucai.com.merchant.framework.logic.BaseLogic;

public class LogicFactory {

    private static final LogicFactory ins = new LogicFactory();

    public static final LogicFactory self() {
        return ins;
    }

    public static enum Type {
        User,
        Ticket,
    }

    private Map<Type, BaseLogic.Factory> mFactorys = new HashMap<Type, BaseLogic.Factory>();
    private Map<Type, BaseLogic> mLogics = new HashMap<Type, BaseLogic>();

    private LogicFactory() {
        mFactorys.put(Type.User,new UserLogic.Factory());
        mFactorys.put(Type.Ticket, new TicketLogic.Factory());
    }

    public BaseLogic get(Type type) {
        if (!mLogics.containsKey(type)) {
            mLogics.put(type, mFactorys.get(type).create());
            mLogics.put(type, mFactorys.get(type).create());
        }
        return mLogics.get(type);
    }


}
