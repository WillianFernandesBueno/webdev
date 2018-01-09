package webdev.api.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import webdev.api.conversor.BigDecimalParamConverter;

import java.io.IOException;
import java.math.BigDecimal;

/**
 *  Deserialização costumizado para o tipo BigDecimal
 *  @author Willian Bueno
 */
public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

    private BigDecimalParamConverter converter = new BigDecimalParamConverter();

    @Override
    public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return converter.fromString(jp.getValueAsString());
    }
}

