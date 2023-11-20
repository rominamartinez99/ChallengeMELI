package com.example.challenge_meli.services;

import com.example.challenge_meli.model.InvalidInputException;
import com.example.challenge_meli.model.InvalidTranslationException;
import com.example.challenge_meli.model.translators.Bits2Morse;
import com.example.challenge_meli.model.translators.Human2Morse;
import com.example.challenge_meli.model.translators.Morse2Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TraductorServiceTest {

    @Autowired
    TraductorService traductorService = new TraductorService();
    Bits2Morse decodeMorse = new Bits2Morse();
    Morse2Human morse2Human = new Morse2Human();
    Human2Morse human2Morse = new Human2Morse();

    @Test
    void morse2HumanTranslate() {
        String morse = "-----.";

        Assertions.assertThrows(InvalidInputException.class, () -> {
            morse2Human.translate(morse);
        });
    }

    @Test
    void morse2Human() throws InvalidInputException {
        String morse = ".... --- .-.. .-  -- . .-.. ..";
        String toHuman = traductorService.morse2Human(morse);

        Assertions.assertEquals("HOLA MELI",toHuman);
    }

    @Test
    void human2Morse() throws InvalidInputException {
        String human = "hola meli";
        String toMorse = traductorService.human2Morse(human);

        Assertions.assertEquals(".... --- .-.. .-  -- . .-.. ..",toMorse);
    }
    @ParameterizedTest
    @ValueSource(strings = {"..1.. ---", "..A.. ---","..!.. ---"})
    void morse2humanInvalidInput(String morse){
        Assertions.assertThrows(InvalidInputException.class, ()-> {
            morse2Human.validateInput(morse);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"hola%meli", "hola meli!"})
    void human2morseInvalidInput(String morse){
        Assertions.assertThrows(InvalidInputException.class, ()-> {
            human2Morse.validateInput(morse);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"110011R001100110000", "110011%001100110000", "1100110 01100110000"})
    void decodeMorseInvalidInput(String bitsInput) {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            decodeMorse.validateInput(bitsInput);
        });
    }

    @BeforeEach
    void setUp() throws InvalidInputException {
        String parisWords = "110011111001111100110000011001111100000110011111001100000110011000001100110011000000000011001111100111110011000001100111110000011001111100110000011001100000110011001100000000001100111110011111001100000110011111000001100111110011000001100110000011001100110000000000";
        traductorService.calibrate(parisWords);
    }

    @Test
    void decodeBits2Morse() throws InvalidInputException, InvalidTranslationException {
        setUp();
        String bitsInput = "110011001100110000011111001111100111110000011001111100110011000001100111110000000011110011111000001100000110011111001100110000011001100";
        String toMorse = traductorService.decodeBits2Morse(bitsInput);

        Assertions.assertEquals(".... --- .-.. .-   -- . .-.. ..",toMorse);
    }
}