package com.example.challenge_meli.services.Jwt;

import com.example.challenge_meli.dto.AuthRequestDto;

import java.util.Map;

public interface AuthService {
    Map<String, String> authRequest(AuthRequestDto authRequestDto);

}