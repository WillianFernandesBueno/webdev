package webdev.api.serializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import webdev.api.conversor.LongParamConverter;

import java.io.IOException;

/**
 * Serialização costumizado para o tipo Long
 * @author Willian Bueno
 */
public class LongSerializer extends JsonSerializer<Long> {

    private LongParamConverter converter = new LongParamConverter();

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (value != null){
            gen.writeString(converter.toString(value));
        }
    }
}

