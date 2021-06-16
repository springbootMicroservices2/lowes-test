package test.lowes.urlShortner.lowesurlShortner.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseConverterTest {
    private BaseConverter baseConversion = new BaseConverter();

    @Test
    public void encode_lessThan62() {
        assertEquals("k", baseConversion.encode(10));
    }

    @Test
    public void encode_moreThan62() {
        assertEquals("bq", baseConversion.encode(78));
    }

    @Test
    public void decode_singleCharacter() {
        assertEquals(11, baseConversion.decode("l"));
    }
}