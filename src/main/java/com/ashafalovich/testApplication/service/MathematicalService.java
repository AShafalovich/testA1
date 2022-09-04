package com.ashafalovich.testApplication.service;

import org.springframework.stereotype.Service;

@Service
public class MathematicalService {

    private double factorial(int n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private double factorialSum(int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += factorial(i);
        }
        return sum;
    }

    public double calculateFactorialFunction(int n) {
        return (1.0 / factorial(n)) * factorialSum(n);
    }
}
