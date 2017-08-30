package org.knowm.xchange.bter.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bter.BTERAuthenticated;
import org.knowm.xchange.bter.dto.BTERBaseResponse;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;

import si.mazi.rescu.ClientConfig;
import si.mazi.rescu.Interceptor;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BTERBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final BTERAuthenticated bter;
  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public BTERBaseService(Exchange exchange) {

    super(exchange);
    ClientConfig clientConfig = new ClientConfig();
    clientConfig.setHttpConnTimeout(exchange.getExchangeSpecification().getHttpReadTimeout());
    clientConfig.setHttpReadTimeout(exchange.getExchangeSpecification().getHttpReadTimeout());

    this.bter = RestProxyFactory.createProxy(BTERAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri() , clientConfig , new Interceptor[0]);
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BTERHmacPostBodyDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  protected <R extends BTERBaseResponse> R handleResponse(R response) {

    if (!response.isResult()) {
      throw new ExchangeException(response.getMessage());
    }

    return response;
  }

}
