package org.knowm.xchange.service.trade.params;

import org.knowm.xchange.currency.CurrencyPair;

import java.math.BigDecimal;

/**
 * Created by lazy_p@163.com on 2017/8/30.
 */
public class DefaultCancelOrderParams implements CancelOrderParams {
    private CurrencyPair currencyPair;
    private String orderId;

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(CurrencyPair currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
