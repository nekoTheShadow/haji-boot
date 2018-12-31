package com.example;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.app.ArgumentResolver;
import com.example.app.Calculator;

@SpringBootApplication
public class HajibootDiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(HajibootDiApplication.class, args);
		
		System.out.print("Enter 2 numbers like 'a b' : ");
		var argumentResolver = context.getBean(ArgumentResolver.class);
		var argument = argumentResolver.resolve(System.in);

		var calculator = context.getBean(Calculator.class);
		int result = calculator.calc(argument.getA(), argument.getB());
		
		System.out.println("result = " + result);
	}

}

