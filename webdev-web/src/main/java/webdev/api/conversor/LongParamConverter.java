package webdev.api.conversor;

import br.com.webdev.util.StringUtil;
import javax.ws.rs.ext.ParamConverter;

public class LongParamConverter implements ParamConverter<Long>
{
    @Override
    public Long fromString(String value)
    {
        if (!StringUtil.isEmpty(value) &&
                value.matches("-?\\d+")){
            return Long.valueOf(value);
        }

        return null;
    }

    @Override
    public String toString(Long value)
    {
        if (value != null){
            return value.toString();
        }
        return null;
    }

}