package com.kshired.url.urlshortener.convert;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Base62Test {
    @Test
    public void base62ConvertTest() {
        Base62 base62 = new Base62();
        String encodedUrl = base62.urlEncoder("12345");
        long test = base62.urlDecoder(encodedUrl);

        assertThat(test).isEqualTo(12345);

    }

}