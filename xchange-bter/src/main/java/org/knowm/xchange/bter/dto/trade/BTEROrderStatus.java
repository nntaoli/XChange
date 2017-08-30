package org.knowm.xchange.bter.dto.trade;

import java.math.BigDecimal;
import java.util.Date;

import org.knowm.xchange.bter.BTERAdapters;
import org.knowm.xchange.bter.dto.BTERBaseResponse;
import org.knowm.xchange.bter.dto.BTEROrderType;
import org.knowm.xchange.currency.CurrencyPair;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by David Henry on 2/19/14.
 */
public class BTEROrderStatus extends BTERBaseResponse {

  private final BTEROrderStatusInfo orderStatusInfo;

  private BTEROrderStatus(@JsonProperty("order") BTEROrderStatusInfo orderStatusInfo, @JsonProperty("result") boolean result,
      @JsonProperty("message") String msg) {

    super(result, msg);
    this.orderStatusInfo = orderStatusInfo;
  }

  public String getId() {

    return orderStatusInfo.getId();
  }

  public String getStatus() {

    return orderStatusInfo.getStatus();
  }

  public CurrencyPair getCurrencyPair() {

    return orderStatusInfo.getCurrencyPair();
  }

  public BTEROrderType getType() {

    return orderStatusInfo.getType();
  }

  public BigDecimal getRate() {

    return orderStatusInfo.getRate();
  }

  public BigDecimal getAmount() {

    return orderStatusInfo.getAmount();
  }

  public BigDecimal getInitialRate() {

    return orderStatusInfo.getInitialRate();
  }

  public BigDecimal getInitialAmount() {

    return orderStatusInfo.getInitialAmount();
  }

  public Date getTimestamp(){
    return new Date(orderStatusInfo.getTimestamp());
  }

  public String toString() {

    return orderStatusInfo.toString();
  }

  public static class BTEROrderStatusInfo {

    private final String id;
    private final String status;
    private final CurrencyPair currencyPair;
    private final BTEROrderType type;
    private final BigDecimal rate;
    private final BigDecimal amount;
    private final BigDecimal initialRate;
    private final BigDecimal initialAmount;
    private final long timestamp;

    private BTEROrderStatusInfo(@JsonProperty("orderNumber") String id, @JsonProperty("status") String status, @JsonProperty("currencyPair") String currencyPair,
        @JsonProperty("type") String type, @JsonProperty("filledRate") BigDecimal rate, @JsonProperty("filledAmount") BigDecimal amount,
        @JsonProperty("initialRate") BigDecimal initialRate, @JsonProperty("initialAmount") BigDecimal initialAmount ,@JsonProperty("timestamp") long timestamp) {

      this.id = id;
      this.status = status;
      this.currencyPair = BTERAdapters.adaptCurrencyPair(currencyPair);
      this.type = type.equals("buy") ? BTEROrderType.BUY : BTEROrderType.SELL;
      this.rate = rate;
      this.amount = amount;
      this.initialRate = initialRate;
      this.initialAmount = initialAmount;
      this.timestamp = timestamp;
    }

    public String getId() {

      return id;
    }

    public String getStatus() {

      return status;
    }

    public CurrencyPair getCurrencyPair() {

      return currencyPair;
    }

    public BTEROrderType getType() {

      return type;
    }

    public BigDecimal getRate() {

      return rate;
    }

    public BigDecimal getAmount() {

      return amount;
    }

    public BigDecimal getInitialRate() {

      return initialRate;
    }

    public BigDecimal getInitialAmount() {

      return initialAmount;
    }

    public long getTimestamp() {
      return timestamp;
    }

    @Override
    public String toString() {

      return "BTEROrderStatusInfo [id=" + id + ", status=" + status + ", currencyPair=" + currencyPair + ", type=" + type + ", rate=" + rate
          + ", amount=" + amount + ", initialRate=" + initialRate + ", initialAmount=" + initialAmount + "]";
    }
  }
}
