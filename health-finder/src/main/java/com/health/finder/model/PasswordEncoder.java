package com.health.finder.model;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
/**
 *
 * @author eleani
 * @author herve
 * Clase que proporciona métodos estáticos para codificar contraseñas utilizando el algoritmo de hash MD5.
 */
public class PasswordEncoder {
    private static final Logger LOGGER = Logger.getLogger(PasswordEncoder.class.getName());
    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private PasswordEncoder() {

    }

    /**
     * Método estático para obtener la representación codificada de una contraseña utilizando MD5.
     *
     * @param input La contraseña en texto plano que se desea codificar.
     * @return La representación codificada de la contraseña en formato hexadecimal.
     */
    public static String getEncodePassword(String input) {

        try {
            // Se obtiene una instancia del algoritmo de hash MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Se calcula el hash de la contraseña
            byte[] hashedPasswordBytes = md.digest(input.getBytes());
            // Se convierte el hash en un número entero sin signo
            BigInteger hashedPasswordBigInt = new BigInteger(1, hashedPasswordBytes);
            // Se convierte el número entero en una cadena hexadecimal
            StringBuilder hashedPasswordHex = new StringBuilder(hashedPasswordBigInt.toString(16));
            // Se asegura de que la cadena tenga 32 caracteres
            while (hashedPasswordHex.length() < 32) {
                hashedPasswordHex.insert(0, "0");
            }
            return hashedPasswordHex.toString();
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            LOGGER.info("EXCEPTION: "+e);
        }
        return input;
    }

}
