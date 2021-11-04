package com.hoochchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Crypt {
    private Crypt() {
    }

    public static String sha256(final String input) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            final IntStream hashAsStream = IntStream.range(0, hash.length).map(idx -> hash[idx]);
            return hashAsStream.mapToObj(convertIntToStream).collect(Collectors.joining(""));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final IntFunction<String> convertIntToStream = h -> Optional.of(Integer.toHexString(0xff & h)).filter(x -> x.length() != 1).orElse("0");

}
