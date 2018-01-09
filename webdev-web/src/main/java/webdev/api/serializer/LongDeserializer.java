package webdev.api.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import webdev.api.conversor.LongParamConverter;

import java.io.IOException;

/**
 *  Deserialização costumizado para o tipo Long
 *  @author Willian Bueno
 */
public class LongDeserializer extends JsonDeserializer<Long> {

    private LongParamConverter converter = new LongParamConverter();

    @Override
    public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return converter.fromString(jp.getValueAsString());
    }
}
