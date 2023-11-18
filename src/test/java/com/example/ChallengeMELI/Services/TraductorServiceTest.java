package com.example.ChallengeMELI.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TraductorServiceTest {

    @Autowired
    TraductorService traductorService = new TraductorService();

    @Test
    void decodeMorse() {
        String bitsInput = "110011111001111100110000011001111100000110011111001100000110011000001100110011000000000011001111100111110011000001100111110000011001111100110000011001100000110011001100000000001100111110011111001100000110011111000001100111110011000001100110000011001100110000000000110011001100110000011111001111100111110000011001111100110011000001100111110000000011110011111000001100000110011111001100110000011001100";
        String toMorse = traductorService.decodeMorse(bitsInput);

        Assertions.assertEquals(".... --- .-.. .-   -- . .-.. ..",toMorse);
    }

    @Test
    void morse2Human() {
    }

    @Test
    void human2Morse() {
    }
}