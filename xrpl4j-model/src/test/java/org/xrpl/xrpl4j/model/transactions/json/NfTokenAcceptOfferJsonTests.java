package org.xrpl.xrpl4j.model.transactions.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.primitives.UnsignedInteger;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.xrpl.xrpl4j.model.AbstractJsonTest;
import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.Hash256;
import org.xrpl.xrpl4j.model.transactions.NfTokenAcceptOffer;
import org.xrpl.xrpl4j.model.transactions.XrpCurrencyAmount;

public class NfTokenAcceptOfferJsonTests extends AbstractJsonTest {

  @Test
  public void testMinimalNfTokenAcceptOfferJson() throws JsonProcessingException, JSONException {

    Hash256 offer = Hash256.of("000B013A95F14B0044F78A264E41713C64B5F89242540EE208C3098E00000D65");
    NfTokenAcceptOffer nfTokenAcceptOffer = NfTokenAcceptOffer.builder()
      .fee(XrpCurrencyAmount.ofDrops(12))
      .account(Address.of("rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59Ba"))
      .sequence(UnsignedInteger.valueOf(12))
      .brokerFee(XrpCurrencyAmount.ofDrops(10))
      .buyOffer(offer)
      .sellOffer(offer)
      .build();

    String json = "{\n" +
      "    \"TransactionType\": \"NFTokenAcceptOffer\",\n" +
      "    \"Account\": \"rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59Ba\",\n" +
      "    \"Fee\": \"12\",\n" +
      "    \"Sequence\": 12,\n" +
      "    \"BuyOffer\": \"000B013A95F14B0044F78A264E41713C64B5F89242540EE208C3098E00000D65\",\n" +
      "    \"SellOffer\": \"000B013A95F14B0044F78A264E41713C64B5F89242540EE208C3098E00000D65\",\n" +
      "    \"Flags\": 2147483648,\n" +
      "    \"BrokerFee\": \"10\"\n" +
      "}";

    assertCanSerializeAndDeserialize(nfTokenAcceptOffer, json);
  }

  @Test
  public void testNfTokenAcceptOfferWithOnlyBuyOffer() throws JsonProcessingException, JSONException {

    Hash256 offer = Hash256.of("000B013A95F14B0044F78A264E41713C64B5F89242540EE208C3098E00000D65");
    NfTokenAcceptOffer nfTokenAcceptOffer = NfTokenAcceptOffer.builder()
      .fee(XrpCurrencyAmount.ofDrops(12))
      .account(Address.of("rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59Ba"))
      .sequence(UnsignedInteger.valueOf(12))
      .buyOffer(offer)
      .build();

    String json = "{\n" +
      "    \"TransactionType\": \"NFTokenAcceptOffer\",\n" +
      "    \"Account\": \"rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59Ba\",\n" +
      "    \"Fee\": \"12\",\n" +
      "    \"Sequence\": 12,\n" +
      "    \"BuyOffer\": \"000B013A95F14B0044F78A264E41713C64B5F89242540EE208C3098E00000D65\",\n" +
      "    \"Flags\": 2147483648\n" +
      "}";

    assertCanSerializeAndDeserialize(nfTokenAcceptOffer, json);
  }
}