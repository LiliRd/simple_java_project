package com.example;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void shouldAddNumbers() {

        int result = App.add(2,3);

        assertEquals(5,result);

    }
}
