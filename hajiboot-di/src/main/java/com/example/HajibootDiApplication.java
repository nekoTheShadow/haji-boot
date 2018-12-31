package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.app.Frontend;

@SpringBootApplication
public class HajibootDiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(HajibootDiApplication.class, args);
		var frontend = context.getBean(Frontend.class);
		frontend.run();
	}

}

