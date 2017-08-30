package org.knowm.xchange.bter.dto.trade;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by David Henry on 2/19/14.
 */
public class BTEROpenOrder {

  private final String id;
  private final String type;
  private final BigDecimal amount;
  private final BigDecimal rate;
    private final String currencyPair;
  private long timestampL;
  private Date timestamp;

  /**
   * Constructor
   *
   * @param id orderId
   */
  private BTEROpenOrder(@JsonProperty("orderNumber") String id, @JsonProperty("type") String type, @JsonProperty("status") String status,
           @JsonProperty("currencyPair") String currencyPair ,  @JsonProperty("amount") BigDecimal amount, @JsonProperty("rate") BigDecimal rate ,
                        @JsonProperty("timestamp") long timestampL) {

      this.id = id;
      this.type = type;
      this.rate = rate;
      this.amount = amount;
      this.currencyPair = currencyPair;
      this.timestampL = timestampL;
      this.timestamp = new Date(timestampL * 1000L);
  }

    public String getId() {

        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public long getTimestampL() {
        return timestampL;
    }

    public void setTimestampL(long timestampL) {
        this.timestampL = timestampL;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
  public String toString() {

    return "BTEROpenOrder [id=" + id + ", type=" + type +  ", amount=" + amount
        + ", rate=" + rate + ", timestramp=" + timestamp + "]";
  }
}
