package com.example;

import org.springframework.stereotype.Service;

@Service
public class AppService {
    public int add (int a, int b){
        System.out.println("add method : a+b!");
        return a+b;
    }

    public int multiply (int a, int b){
        System.out.println("multiply method : a*b!");
        return a*b;
    }

    public int divide (int a, int b){
        System.out.println("divide method : a/b!");
        return a/b;
    }

}
