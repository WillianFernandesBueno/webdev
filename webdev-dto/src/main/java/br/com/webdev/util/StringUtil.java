package br.com.webdev.util;

import java.util.Objects;

public class StringUtil {
    /**
     * Verifica se o valor encontra-se vazio ou nulo
     * @param valor
     * @return true quando vazio ou null
     */
    public static boolean isEmpty(String valor)
    {
        return Objects.isNull(valor) || valor.isEmpty();
    }
}
