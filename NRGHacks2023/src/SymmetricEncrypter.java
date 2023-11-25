import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;




public class SymmetricEncrypter {
    private KeyGenerator keyGenerator;
    private SecretKey key;
    private Cipher cipher;
    private IvParameterSpec iv;


    public SymmetricEncrypter() throws Exception{
        this.setup();
    }


    private void setup() throws Exception{
        this.keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        this.key = new SecretKeySpec(new byte[]{15, 119, 104, 9, 124, -125, 28, -110, 82, -25, -106, -41, -37, -120, 69, -80, -62, 36, -74, -76, 1, -62, 115, -93, 74, -85, -79, 91, 57, 101, -17, 27}, "AES");
        this.iv = new IvParameterSpec(new byte[16]);
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");


    }


    public byte[] encrypt(String input) throws Exception{
        return (encrypt(input, this.key, this.iv, this.cipher));
    }


    private byte[] encrypt(String input, SecretKey key, IvParameterSpec iv, Cipher cipher) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(input.getBytes());
    }


    public String decrypt(String encryptedString) throws Exception{
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
        return (decrypt(encryptedBytes, this.key, this.iv, this.cipher));
    }


    private String decrypt(byte[] encryptedBytes, SecretKey key, IvParameterSpec iv, Cipher cipher) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }




}
