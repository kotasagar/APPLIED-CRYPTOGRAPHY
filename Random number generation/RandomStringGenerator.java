import java.security.SecureRandom;
import java.util.Scanner;

public class RandomStringGenerator 
{
    // Define the subset of characters to use
    private static final String CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom random = new SecureRandom();
    
    /**
     * Generates a random string of given length using the defined character set.
     * 
     * @param length Length of the random string to generate
     * @return Randomly generated string
     */

    public static String generateRandomString(int length) 
    {
        if (length <= 0) 
        {
            throw new IllegalArgumentException("Length must be positive");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) 
        {
            int randomIndex = random.nextInt(CHAR_SET.length());
            sb.append(CHAR_SET.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.err.println("Enter the No Of Digits to Generate Random String : ");
        int n=sc.nextInt();
        // Example usage: generate a random string of length 10
        String randomStr = generateRandomString(n);
        System.out.println("Random String: " + randomStr);
    }
}