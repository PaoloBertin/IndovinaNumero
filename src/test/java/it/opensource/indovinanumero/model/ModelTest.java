package it.opensource.indovinanumero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void init() {

    }

    @Test
    void tentativoTest() {

        Model model = new Model();
        model.init();

        int tentativiFatti = model.getTMAX();
    }
}