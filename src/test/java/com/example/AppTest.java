package com.example;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    App myApp=new App();
    @Test
    void shouldAddNumbers() {
        int result = myApp.add(2,3);
        assertEquals(5,result);

    }
}
