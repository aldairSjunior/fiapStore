package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.util.CriptografiaUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class CriptografiaTeste {

    public static void main(String[] args) {
        try {
            System.out.println(CriptografiaUtils.criptografar("123456"));
            System.out.println(CriptografiaUtils.criptografar("123456"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
