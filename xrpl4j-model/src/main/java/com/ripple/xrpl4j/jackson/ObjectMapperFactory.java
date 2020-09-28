package com.ripple.xrpl4j.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.ripple.xrpl4j.jackson.modules.AddressModule;
import com.ripple.xrpl4j.jackson.modules.FlagsModule;
import com.ripple.xrpl4j.jackson.modules.Hash256Module;
import com.ripple.xrpl4j.jackson.modules.TransactionTypeModule;
import com.ripple.xrpl4j.jackson.modules.XrpCurrencyAmountModule;

/**
 * A factory for constructing instances of {@link ObjectMapper} for all connector components.
 */
public class ObjectMapperFactory {

  /**
   * Construct an {@link ObjectMapper} that can be used to serialize and deserialize JSON where all numbers are Strings,
   * by default.
   *
   * @return An {@link ObjectMapper}.
   */
  public static ObjectMapper create() {

    return JsonMapper.builder()
      .addModule(new Jdk8Module())
      .addModule(new GuavaModule())
      .addModule(new AddressModule())
      .addModule(new Hash256Module())
      .addModule(new XrpCurrencyAmountModule())
      .addModule(new TransactionTypeModule())
      .addModule(new FlagsModule())
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      // Even though `false`` is the default setting for WRITE_NUMBERS_AS_STRINGS, we overtly set it here to alert
      // the reader that this value must be set this way in order to easily support Problems JSON, which per
      // https://tools.ietf.org/html/rfc7807#section-3.1 is specified to be a number (and not a String). This means
      // that we must be careful to decide if we want to serialize any other JSON number as a String (which generally
      // we do.
      .configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true)
      .configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
      .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
      .build();
  }
}
