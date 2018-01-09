package webdev.api.providers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import webdev.api.serializer.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;

/**
 * Configuração Jackson
 * @author Willian Bueno
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfiguration implements ContextResolver<ObjectMapper> {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        // Inclui na serialização apenas as propriedades não vazias
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        // Utiliza os fields por padrão na serialização e deserialização
        MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

        // Ignora propriedades desconhecidas
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Aceita strings vazia como objetos nulos
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        // desabilita o tráfego de datas como timestamps
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        SimpleModule module = new SimpleModule();
        module.setDeserializers(new CustomDeserializers());

        // Define uma conversão padrão para objetos Long
        module.addDeserializer(Long.class, new LongDeserializer());
        module.addSerializer(Long.class,new LongSerializer());

        // Define uma conversão padrão para objetos BigDecimal
        module.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());
        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());

        MAPPER.registerModule(module);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return null;
    }
}
