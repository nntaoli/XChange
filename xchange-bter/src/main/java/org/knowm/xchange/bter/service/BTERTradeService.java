package org.knowm.xchange.bter.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bter.BTERAdapters;
import org.knowm.xchange.bter.dto.trade.BTEROpenOrders;
import org.knowm.xchange.bter.dto.trade.BTEROrderStatus;
import org.knowm.xchange.bter.dto.trade.BTERTrade;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.*;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

public class BTERTradeService extends BTERTradeServiceRaw implements TradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BTERTradeService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    return getOpenOrders(createOpenOrdersParams());
  }

  @Override
  public OpenOrders getOpenOrders(
      OpenOrdersParams params) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    BTEROpenOrders openOrders = super.getBTEROpenOrders();
    Collection<CurrencyPair> currencyPairs = exchange.getExchangeSymbols();

    return BTERAdapters.adaptOpenOrders(openOrders, currencyPairs);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {

    throw new NotAvailableFromExchangeException();
  }

  /**
   * Submits a Limit Order to be executed on the BTER Exchange for the desired market defined by {@code CurrencyPair}. WARNING - BTER will return true
   * regardless of whether or not an order actually gets created. The reason for this is that orders are simply submitted to a queue in their
   * back-end. One example for why an order might not get created is because there are insufficient funds. The best attempt you can make to confirm
   * that the order was created is to poll {@link #getOpenOrders}. However, if the order is created and executed before it is caught in its open state
   * from calling {@link #getOpenOrders} then the only way to confirm would be confirm the expected difference in funds available for your account.
   *
   * @return String "true"/"false" Used to determine if the order request was submitted successfully.
   */
  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    return String.valueOf(super.placeBTERLimitOrder(limitOrder));
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    throw new NotYetImplementedForExchangeException("Please Invoke cancelOrder(CancelOrderParams)");
  }

  @Override
  public boolean cancelOrder(CancelOrderParams orderParams) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    if (orderParams instanceof CancelOrderByIdParams) {
      return cancelOrder(((CancelOrderByIdParams) orderParams).orderId);
    } else if (orderParams instanceof DefaultCancelOrderParams){
      DefaultCancelOrderParams defaultCancelOrderParams = (DefaultCancelOrderParams)orderParams;
      String currencyPair = BTERAdapters.adaptPair(defaultCancelOrderParams.getCurrencyPair());
      return super.cancelOrder(defaultCancelOrderParams.getOrderId() , currencyPair);
    }
    return false;
  }

  /**
   * Required parameter: {@link TradeHistoryParamCurrencyPair}
   */
  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws ExchangeException, IOException {

    CurrencyPair pair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
    List<BTERTrade> userTrades = getBTERTradeHistory(pair).getTrades();

    return BTERAdapters.adaptUserTrades(userTrades);
  }

  @Override
  public TradeHistoryParamCurrencyPair createTradeHistoryParams() {

    return new DefaultTradeHistoryParamCurrencyPair();
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {
    return null;
  }

  @Override
  public Collection<Order> getOrder(
      String... orderIds) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    if (orderIds.length != 2){
      throw new ExchangeException("parameter must equal two");
    }
    String orderId = orderIds[0];
    CurrencyPair pair = new CurrencyPair(orderIds[1]);
    BTEROrderStatus orderStatus = super.getBTEROrderStatus(orderId , BTERAdapters.adaptPair(pair));

    List<Order> orderList = new ArrayList<>();
    LimitOrder limitOrder = new LimitOrder.Builder(BTERAdapters.adaptOrderType(orderStatus.getType()) , pair)
            .id(orderId)
            .limitPrice(orderStatus.getInitialRate())
            .originAmount(orderStatus.getInitialAmount())
            .dealAmount(orderStatus.getAmount())
            .averagePrice(orderStatus.getRate())
            .orderStatus(BTERAdapters.adaptOrderStatus(orderStatus.getStatus()))
            .timestamp(orderStatus.getTimestamp())
            .build2();

    orderList.add(limitOrder);
    return orderList;
  }

}
