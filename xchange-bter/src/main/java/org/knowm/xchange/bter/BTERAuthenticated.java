package org.knowm.xchange.bter;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.bter.dto.BTEROrderType;
import org.knowm.xchange.bter.dto.account.BTERFunds;
import org.knowm.xchange.bter.dto.trade.*;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("api2/1")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface BTERAuthenticated extends BTER {

  @POST
  @Path("private/balances")
  BTERFunds getFunds(@HeaderParam("KEY") String apiKey, @HeaderParam("SIGN") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("private/cancelOrder")
  BTERCancelOrderResult cancelOrder(@FormParam("orderNumber") String orderId, @FormParam("currencyPair") String currencyPair,
                                    @HeaderParam("KEY") String apiKey, @HeaderParam("SIGN") ParamsDigest signer,
                                    @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("private/{type}")
  BTERPlaceOrderReturn placeOrder(@FormParam("currencyPair") String pair, @PathParam("type") BTEROrderType type, @FormParam("rate") BigDecimal rate,
                                  @FormParam("amount") BigDecimal amount, @HeaderParam("KEY") String apiKey, @HeaderParam("SIGN") ParamsDigest signer,
                                  @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("private/openOrders")
  BTEROpenOrders getOpenOrders(@HeaderParam("KEY") String apiKey, @HeaderParam("SIGN") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("private/mytrades")
  BTERTradeHistoryReturn getUserTradeHistory(@HeaderParam("KEY") String apiKey, @HeaderParam("SIGN") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("pair") String pair) throws IOException;

  @POST
  @Path("private/getOrder")
  BTEROrderStatus getOrderStatus(@FormParam("orderNumber") String orderId, @FormParam("currencyPair") String currencyPair ,
                                 @HeaderParam("KEY") String apiKey, @HeaderParam("SIGN") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws IOException;
}
