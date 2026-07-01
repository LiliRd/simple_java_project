package com.example;

import org.springframework.stereotype.Service;

@Service
public class AppService {
    public int add (int a, int b){
        System.out.println("Hello, Lili! Jenkins is working!");
        return a+b;
    }

    public int multiply (int a, int b){
        System.out.println("Hello, Lili! Jenkins is working!");
        return a*b;
    }

    public int divide (int a, int b){
        System.out.println("Hello, Lili! Jenkins is working!");
        return a/b;
    }

}
