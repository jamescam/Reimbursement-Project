import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.security.MessageDigest;

public class HashPassword {

    /* Method that takes a user's plain text password and a 16 byte array.
     * Uses the message digest class, which implements the message digest algorithm and updates
     * the digest with a 16 byte salt, to produce a hash. Then uses the getEncoder method to
     * convert the hash into a string.
     * @returns a String
     */
    public String hashAlgo(String plainTextPass, byte[] salt) throws NoSuchAlgorithmException {

        HashPassword hp = new HashPassword();
        hp.encodeSalt(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        byte[] hashedPassword = md.digest(plainTextPass.getBytes(StandardCharsets.UTF_8));

        String hashedPasswordEncodedToString = Base64.getEncoder().encodeToString(hashedPassword);

        return hashedPasswordEncodedToString;
    }

    /*Method used to generate the salt:
     * an additional randomized bytes of data used in conjunction with a one-way algorithm.
     * @return 16 byte array.
     */
    public byte[] salt() {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];

        random.nextBytes(salt);

        return salt;
    }

    /*Encodes the 16 byte salt into a string.
     *@return String
     */

    public String encodeSalt(byte[] salt){

        String saltEncodedToString = Base64.getEncoder().encodeToString(salt);

        return saltEncodedToString;
    }
}
