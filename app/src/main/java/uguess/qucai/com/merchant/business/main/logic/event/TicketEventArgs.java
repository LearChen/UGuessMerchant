/*
 *
 *  *
 *  *   Copyright(c) 2015, QuCai8, Inc. All rights reserved.
 *  *   This software is the confidential and proprietary information of QuCai8, Inc.
 *  *   You shall not disclose such Confidential Information and shall use it only in
 *  *   accordance with the terms of the license agreement you entered into with QuCai8.
 *  *
 *
 *
 */

package uguess.qucai.com.merchant.business.main.logic.event;

import uguess.qucai.com.merchant.business.common.module.Ticket;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.event.StatusEventArgs;

/**
 * Created by NO1 on 2015/2/6.
 */
public class TicketEventArgs extends StatusEventArgs {

    private Ticket result = null;
    /**
     * 用户信息列表
     */
    public TicketEventArgs(Ticket ticket, OperErrorCode errCode) {
        super(errCode);
        result = ticket;
    }

    /**
     * 错误返回
     *
     * @param errCode
     */
    public TicketEventArgs(OperErrorCode errCode) {
        super(errCode);
    }

    /**
     * 正确返回
     *
     * @param ticket
     */
    public TicketEventArgs(Ticket ticket) {
        super(OperErrorCode.Success);
        result = ticket;
    }

    public Ticket getResult() {
        return result;
    }

    @Override
    public OperErrorCode getErrCode() {
        return super.getErrCode();
    }

}
