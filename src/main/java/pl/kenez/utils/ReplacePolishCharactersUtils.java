package pl.kenez.utils;

import org.apache.commons.lang3.StringUtils;

public class ReplacePolishCharactersUtils {

    private ReplacePolishCharactersUtils() {
    }

    public static String replacePolishCharacters(final String text) {
        return StringUtils.stripAccents(text);
    }

}
