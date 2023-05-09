package com.example.techprom_vol;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES {
    private final SecretKey privateKey = getKey();

    private SecretKey getKey() {
        return new SecretKeySpec("IlOvetechProM095".getBytes(), "AES");
    }

    public String cipher (String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(password.getBytes());
        String cipherPass = new String(bytes, StandardCharsets.UTF_8);

        return cipherPass;
    }
}
