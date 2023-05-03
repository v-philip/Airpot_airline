package input;

import DTO.Airport;

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

    public static Airport InsertAirport(String shortf,String str){
        Airport newAirport = new Airport();

        System.out.println("airportShortForm\n");
        String short_form =keyboard.nextLine();
        while(!short_form.matches(shortf))
        {
            System.out.println("invalid short form");
            System.out.println("please enter a valid short form ");
            short_form = keyboard.nextLine();
        }
        newAirport.setAirport_short_form(short_form);

        System.out.println("enter there city that the airports in ");
        String city =keyboard.nextLine();
        while(!short_form.matches(str))
        {
            System.out.println("please enter a valid city name ");
            city = keyboard.nextLine();
        }
        newAirport.setAirport_city(city);


        System.out.println("Enter the country that the airport is in ");
        String airportCountry = keyboard.next();
        while(!airportCountry.matches(str))
        {
            System.out.println("please enter a valid city name ");
            airportCountry = keyboard.nextLine();
        }
        newAirport.setAirport_country(airportCountry);

        return newAirport;
    }
}
