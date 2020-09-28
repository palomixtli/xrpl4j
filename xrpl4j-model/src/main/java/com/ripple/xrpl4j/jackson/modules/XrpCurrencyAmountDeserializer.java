package com.ripple.xrpl4j.jackson.modules;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ripple.xrpl4j.transactions.Address;
import com.ripple.xrpl4j.transactions.XrpCurrencyAmount;

import java.io.IOException;

public class XrpCurrencyAmountDeserializer extends StdDeserializer<XrpCurrencyAmount> {

  public XrpCurrencyAmountDeserializer() {
    super(XrpCurrencyAmount.class);
  }

  @Override
  public XrpCurrencyAmount deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
    return XrpCurrencyAmount.of(jsonParser.getText());
  }
}
