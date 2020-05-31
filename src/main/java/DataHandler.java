import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class DataHandler {

    private static Map<String, Account> accountMap = new HashMap<>();

    public static boolean addAccount (String email, String password){
        if (!accountMap.containsKey(email)) {
            accountMap.put(email, new Account(email, toSHA512(password, "$4LT")));
            return true;
        }
        return false;
    }

    public static Map<String, Account> getAccountMap() {
        return accountMap;
    }

    public static String toSHA512(String password, String salt){
        String sha512Converted = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            sha512Converted = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha512Converted;
    }


}
