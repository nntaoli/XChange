package org.knowm.xchange.jubi.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by lazy_p@163.com on 2017/8/29.
 */
public class JubiDepth {
    private final BigDecimal[][] asks;
    private final BigDecimal[][] bids;
    private final Date timestamp;

    public JubiDepth(@JsonProperty("asks") final BigDecimal[][] asks, @JsonProperty("bids") final BigDecimal[][] bids,
                     @JsonProperty(required = false, value = "timestamp") Date timestamp) {

        this.asks = asks;
        this.bids = bids;
        this.timestamp = timestamp;
    }

    public BigDecimal[][] getAsks() {

        return asks;
    }

    public BigDecimal[][] getBids() {

        return bids;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {

        return "JubiDepth [asks=" + Arrays.toString(asks) + ", bids=" + Arrays.toString(bids) + "]";
    }
}
