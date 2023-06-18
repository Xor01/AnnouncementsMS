import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public final class PasswordHandler {

    private final String password;
    private String hashedPassword;

    public PasswordHandler(String password){
        this.password = password;
        try{
            this.hashedPassword = encrypt(password);
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException){
            System.err.println("Error While Processing ");
            System.exit(1);
        }
    }

    public String encrypt(String password) throws NoSuchAlgorithmException {
         try{
             MessageDigest mg = MessageDigest.getInstance("sha-256");

             byte [] messageDigest = mg.digest(password.getBytes());

             BigInteger bigInteger = new BigInteger(1, messageDigest);

             return bigInteger.toString(16);
         }
         catch(NoSuchAlgorithmException wrongAlg){
             System.out.println("[+] No Such Algorithm Found [+]");
             System.err.println("Error While Processing ");
         }
         throw new NoSuchAlgorithmException("could not perform calculation");
    }

    public String getPassword(){
        return this.password;
    }

    public String getHashedPassword(){
        return this.hashedPassword;
    }

    public boolean comparePasswords(String password1, String password2){
        return password1.equals(password2);
    }
}
