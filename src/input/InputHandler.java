package input;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner keyboard =  new Scanner(System.in);

    public static String getAndValidateEnumOptions(String regex, int enumLength, String validOptions)
    {
        String userInput = keyboard.nextLine().trim();

        while(!userInput.matches(regex) || Integer.parseInt(userInput) > enumLength - 1)
        {
            System.out.println("Please enter a valid option" + validOptions + ": ");
            userInput = keyboard.nextLine().trim();
        }

        return userInput;
    }
}
