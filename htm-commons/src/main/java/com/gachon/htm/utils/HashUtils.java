package com.gachon.htm.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashUtils {

    private static final HashFunction hashFunction = Hashing.murmur3_128();

    public static String hashEncode(String string) {
        return String.valueOf(hashFunction.hashString(string, StandardCharsets.UTF_8));
    }
}
