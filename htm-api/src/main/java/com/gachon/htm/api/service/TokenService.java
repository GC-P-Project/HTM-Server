package com.gachon.htm.api.service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.gachon.htm.domain.model.Token;
import com.gachon.htm.domain.model.User;
import com.gachon.htm.domain.repository.token.TokenRepository;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class TokenService {

    private final HashFunction hashFunction = Hashing.murmur3_128();
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = Objects.requireNonNull(tokenRepository, "tokenRepository");
    }

    public Token find(String token) {
        Optional<Token> optToken = tokenRepository.findByToken(token);
        return optToken.orElse(null);
    }

    public Token insert(User user) {
        long timeStamp = System.currentTimeMillis();
        String token = String.valueOf(hashFunction.hashString(StringUtils.join(timeStamp, user.getId(), user.getEmail()),
                                                              StandardCharsets.UTF_8));
        return new Token(token, user.getId());
    }
}
