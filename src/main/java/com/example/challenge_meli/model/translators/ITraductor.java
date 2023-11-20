package com.example.challenge_meli.model.translators;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;

public interface ITraductor {
    void validateInput(String input)throws InvalidInputException;
    String translate(String text) throws InvalidInputException, InvalidTranslationException;
}
