package com.example;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.app.Calculator;

@SpringBootApplication
public class HajibootDiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(HajibootDiApplication.class, args);
		var scanner = new Scanner(System.in);
		
		System.out.print("Enter 2 numbers like 'a b' : ");
		int a = scanner.nextInt(), b = scanner.nextInt();
		
		var calculator = context.getBean(Calculator.class);
		int result = calculator.calc(a, b);
		
		System.out.println("result = " + result);
	}

}

