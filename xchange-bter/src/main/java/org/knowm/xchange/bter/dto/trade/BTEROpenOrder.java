package org.knowm.xchange.bter.dto.trade;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by David Henry on 2/19/14.
 */
public class BTEROpenOrder {

  private final String id;
  private final String sellCurrency;
  private final String buyCurrency;
  private final BigDecimal sellAmount;
  private final BigDecimal buyAmount;
  private long timestampL;
  private Date timestamp;

  /**
   * Constructor
   *
   * @param id orderId
   * @param sellAmount amount to sell
   * @param buyAmount amount to buy
   */
  private BTEROpenOrder(@JsonProperty("id") String id, @JsonProperty("sell_type") String sellCurrency, @JsonProperty("buy_type") String buyCurrency,
      @JsonProperty("sell_amount") BigDecimal sellAmount, @JsonProperty("buy_amount") BigDecimal buyAmount , @JsonProperty("timestamp") long timestampL) {

      this.id = id;
      this.sellCurrency = sellCurrency;
      this.buyCurrency = buyCurrency;
      this.sellAmount = sellAmount;
      this.buyAmount = buyAmount;
      this.timestampL = timestampL;
      this.timestamp = new Date(timestampL * 1000L);
  }

  public String getId() {

    return id;
  }

  public String getSellCurrency() {

    return sellCurrency;
  }

  public String getBuyCurrency() {

    return buyCurrency;
  }

  public BigDecimal getSellAmount() {

    return sellAmount;
  }

  public BigDecimal getBuyAmount() {

    return buyAmount;
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

    return "BTEROpenOrder [id=" + id + ", sellCurrency=" + sellCurrency + ", buyCurrency=" + buyCurrency + ", sellAmount=" + sellAmount
        + ", buyAmount=" + buyAmount + ", timestramp=" + timestamp + "]";
  }
}
