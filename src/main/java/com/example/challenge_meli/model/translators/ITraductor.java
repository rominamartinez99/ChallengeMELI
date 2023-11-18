package com.example.challenge_meli.model.translators;

public interface ITraductor {
    void validateInput(String bitSequence);
    String translate(String bitSequence);
}
