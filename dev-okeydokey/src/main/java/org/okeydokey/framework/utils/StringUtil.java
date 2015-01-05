package org.okeydokey.framework.utils;

public class StringUtil {

	public static boolean isEmpty(String input) {
		
		if (input == null) {
			return true;
		}
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) > ' ') {
				return false;
			}
		}

		return true;
	}

	public static boolean equals(String a, String b) {
		if (a == null) {
			return b == null;
		} else {
			return a.equals(b);
		}
	}
}