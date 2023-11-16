package com.example.ChallengeMELI.Model.Translators;

public interface ITraductor {
    void validateInput(String bitSequence);
    String translate(String bitSequence);
}
