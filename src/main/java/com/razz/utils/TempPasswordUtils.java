package com.razz.utils;

import org.springframework.stereotype.Component;

@Component
public class TempPasswordUtils {

	public static String generateTempPassword(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		System.out.println(sb);
		return sb.toString();

	}
}
