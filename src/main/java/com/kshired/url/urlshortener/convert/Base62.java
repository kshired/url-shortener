package com.kshired.url.urlshortener.convert;

public class Base62 {
    private final String URL_PREFIX = "http://localhost:8080/short/";
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private String encoding(long param) {
        StringBuffer sb = new StringBuffer();
        while (param > 0) {
            sb.append(BASE62.charAt((int) (param % 62)));
            param /= 62;
        }
        return URL_PREFIX + sb.toString();
    }

    private long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE62.indexOf(param.charAt(i)) * power;
            power *= 62;
        }
        return sum;
    }

    public String urlEncoder(String seqStr) {
        return encoding(Integer.valueOf(seqStr));
    }

    public long urlDecoder(String encodeStr) {
        if (encodeStr.trim().startsWith(URL_PREFIX)) {
            encodeStr = encodeStr.replace(URL_PREFIX, "");
        }
        return decoding(encodeStr);
    }
}
