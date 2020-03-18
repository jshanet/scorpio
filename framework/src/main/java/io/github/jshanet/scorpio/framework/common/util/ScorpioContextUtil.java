package io.github.jshanet.scorpio.framework.common.util;

import io.github.jshanet.scorpio.framework.common.context.ScorpioContext;
import io.github.jshanet.scorpio.framework.common.context.ScorpioContextThreadLocal;

/**
 * @author seanjiang
 * @date 2019/12/25
 */
public class ScorpioContextUtil {

    public static void setContext(ScorpioContext weBankContext) {
        ScorpioContextThreadLocal.set(weBankContext);

    }

    public static ScorpioContext getContext() {
        return ScorpioContextThreadLocal.get();
    }

    public static void unsetContext() {
        ScorpioContextThreadLocal.unset();
    }

    public static String getBizSeqNo() {
        ScorpioContext context = ScorpioContextThreadLocal.get();
        String bizSeqNo = null;
        if (context != null) {
            bizSeqNo = context.getBizSeqNo();
        }
        return bizSeqNo;
    }

    public static void setBizSeqNo(String bizSeqNo) {
        ScorpioContext context = ScorpioContextThreadLocal.get();

        if (context == null) {
            context = new ScorpioContext();
            setContext(context);
        }
        context.setBizSeqNo(bizSeqNo);

    }

    public static String getTenantId() {
        ScorpioContext context = ScorpioContextThreadLocal.get();
        String tenantId = null;
        if (context != null) {
            tenantId = context.getTenantId();
        }
        return tenantId;
    }

    public static void setTenantId(String tenantId) {
        ScorpioContext context = ScorpioContextThreadLocal.get();

        if (context == null) {
            context = new ScorpioContext();
            setContext(context);
        }
        context.setTenantId(tenantId);

    }
}