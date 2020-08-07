package com.trinity.digitalEntryPass.service;

import org.springframework.stereotype.Service;

@Service
public class Demo {
	public void encryptTest() {
		final String secretKey = "Oh Mickey, you're so fine You're so fine you blow my mind, hey Mickey,Hey Mickey";

		String originalString = "6F_EXIT";
		String encryptedString = AES.encrypt(originalString, secretKey);
		String decryptedString = AES.decrypt(encryptedString, secretKey);

		System.out.println(originalString);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
	}
}
