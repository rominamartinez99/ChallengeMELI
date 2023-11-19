package com.example.challenge_meli.dto;

import java.util.List;

public record User(String username, String password, List<String> roles) {
}