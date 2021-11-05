package com.hoochchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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

//    public static String sha256(String input) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            //Applies sha256 to our input,
//            byte[] hash = digest.digest(input.getBytes("UTF-8"));
//            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
//            for (int i = 0; i < hash.length; i++) {
//                String hex = Integer.toHexString(0xff & hash[i]);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    private static final IntFunction<String> convertIntToStream = h -> {
        final String hex = Integer.toHexString(0xff & h);
        return hex.length() == 1 ? "0" + hex : hex;
    };

}
