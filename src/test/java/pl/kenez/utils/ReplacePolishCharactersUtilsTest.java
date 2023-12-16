package pl.kenez.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReplacePolishCharactersUtilsTest {

    @Test
    void shouldConvertPolishCharacters() {
        final String result = ReplacePolishCharactersUtils.replacePolishCharacters("ąęćśźżó");

        assertThat(result).isEqualTo("aecszzo");
    }

}