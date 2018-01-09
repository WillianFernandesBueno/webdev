package webdev.api.serializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import webdev.api.conversor.BigDecimalParamConverter;

import java.io.IOException;
import java.math.BigDecimal;

/**
 *  Serialização costumizado para o tipo BigDecimal
 *  @author Willian Bueno
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    private BigDecimalParamConverter converter = new BigDecimalParamConverter();

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (value != null){
            gen.writeString(converter.toString(value));
        }
    }
}
