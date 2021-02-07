package com.kshired.url.urlshortener.convert;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Base62Test {
    @Test
    public void base62ConvertTest() {
        //given
        Base62 base62 = new Base62();
        String testStr = "12345";

        //when
        String encodedUrl = base62.urlEncoder(testStr);
        long test = base62.urlDecoder(encodedUrl);

        // then
        assertThat(test).isEqualTo(Long.parseLong(testStr));
    }

}