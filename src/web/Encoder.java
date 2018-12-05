package web;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encoder {

    public static String encode(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(pass.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(digest);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}

