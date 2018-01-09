package webdev.api.conversor;

import br.com.webdev.util.StringUtil;

import javax.ws.rs.ext.ParamConverter;
import java.math.BigDecimal;

/**
 * Realiza a Conversao de parametros de BigDecimal
 */
public class BigDecimalParamConverter implements ParamConverter<BigDecimal>{

    @Override
    public BigDecimal fromString(String value) {
        if (!StringUtil.isEmpty(value) &&
                value.matches("-?\\d+(\\.\\d+)?")){
            return new BigDecimal(value);
        }

        return null;
    }

    @Override
    public String toString(BigDecimal value) {
        return null;
    }
}
