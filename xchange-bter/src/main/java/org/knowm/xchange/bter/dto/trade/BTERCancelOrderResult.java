package org.knowm.xchange.bter.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bter.dto.BTERBaseResponse;

/**
 * Created by lazy_p@163.com on 2017/8/30.
 */
public class BTERCancelOrderResult extends BTERBaseResponse {
    public BTERCancelOrderResult(@JsonProperty("result") boolean result, @JsonProperty("message") String message) {
        super(result, message);
    }
}
