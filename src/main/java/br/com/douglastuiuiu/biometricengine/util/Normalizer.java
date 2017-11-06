package br.com.douglastuiuiu.biometricengine.util;

/**
 * @author douglas
 * @since 21/03/2017
 */
public class Normalizer {

    private Normalizer() {
    }

    /**
     * Remove qualquer caracteres que não sejam numeros
     *
     * @param value
     * @return valor normalizado somente com números
     */
    public static String normalizeNumbers(String value) {
        if (value == null || value.isEmpty())
            return value;
        return value.replaceAll("[^0-9]", "");
    }

    /**
     * Remove caracteres especiais, acentos, cedilhas e espaços extras
     *
     * @param value
     * @return valor normalizado
     */
    public static String normalizeText(String value) {
        if (value == null || value.isEmpty())
            return value;
        value = removeAcentoECedilha(value);
        value = removerCaracteresEspeciais(value);
        value = removerEspacosExtras(value);
        return value;
    }

    private static String removeAcentoECedilha(String value) {
        return java.text.Normalizer.normalize(value, java.text.Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    private static String removerEspacosExtras(String value) {
        return value.replaceAll("  ", " ").trim();
    }

    public static String removerCaracteresEspeciais(String value) {
        return value.replaceAll("[^\\w\\s]", "");
    }

    /**
     * Remove caracteres do tipo numérico
     *
     * @param value
     * @return valor normalizado
     */
    public static String removeNumeros(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.replaceAll("\\d", "");
    }
}
