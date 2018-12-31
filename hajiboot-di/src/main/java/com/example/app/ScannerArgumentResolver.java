package com.example.app;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerArgumentResolver implements ArgumentResolver{
	@Override
	public Argument resolve(InputStream stream) {
		var scanner = new Scanner(stream);
		int a = scanner.nextInt(), b = scanner.nextInt();
		return new Argument(a, b);
	}
}
