package utility;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class RandomData {

    public static Faker faker = new Faker(new Locale("sk"));
    public static final String NON_ALLOWED_CHARS = "(?i).*['ë()].*"; //non allowed signs to be collected in the brackets


    /**
     * Generates first name of the person
     * @return String
     */
    public static String generateFirstName(){
        String firstName = faker.name().firstName();

        while (firstName.matches(NON_ALLOWED_CHARS)){
            firstName = faker.name().firstName();
        }

        return "AutoTest "+firstName;
    }

    /**
     * Generates last name of the person
     * @return String
     */
    public static String generateLastName() {
        String lastName = faker.name().lastName();

        while (lastName.matches(NON_ALLOWED_CHARS)){
            lastName = faker.name().lastName();
        }

        return lastName;
    }

    /**
     * Generates random email with domain "@umpost.de"
     * @return String
     */
    public static String generateEmail(){
        return faker.regexify("[a-z0-9]{4,11}").concat("@accenture.com");
    }

    /**
     * Generates random postal code
     * @return String
     */
    public static String generatePostCode () {
        return faker.numerify("#####");
    }

    /**
     * Generates random city
     * @return String
     */
    public static String generateCity () {
        String city = faker.address().city();

        while (city.matches(NON_ALLOWED_CHARS)){
            city = faker.address().city();
        }

        return city;
    }

    /**
     * Generates random country
     * @return String
     */
    public static String generateCountry() {
        String country = faker.address().country();

        while (country.matches(NON_ALLOWED_CHARS)){
            country = faker.address().country();
        }

        return country;
    }

    /**
     * Generates random street
     * @return String
     */
    public static String generateStreet() {
        String streetName = faker.address().streetName();

        while (streetName.matches(NON_ALLOWED_CHARS)){
            streetName = faker.address().streetName();
        }

        return streetName;
    }

    /**
     * Generates random street number
     * @return String
     */
    public static String generateStreetNumber(){
        return faker.regexify("[0-9]{1,2}");
    }

    /**
     * Generates random mobile phone number
     * @return String
     */
    public static String generateMobileNumber(){
        return faker.numerify("09########");
    }

    /**
     * Generates random password with properties:
     * At least 1 digit, 1 letter (upper case), 1 letter (lower case), 1 special character
     * Min length: 8, Max length: 15
     * @return String
     */
    public static String generatePassword(){
        return faker.regexify("[0-9][A-Z][a-z][!@#_][a-z]([a-zA-Z0-9]){4,10}");
    }

    /**
     * Generates random date of birth between 1950 - 2006
     * @return String
     */
    public static String generateBirthDate() {
        int yearBegin = 1950;
        int yearEnd = 2006-yearBegin;

        return ""+(10 + (int)(Math.random() * 17)+".0"+(1 + (int)(Math.random() * 9)
                +"."+(yearBegin + (int)(Math.random() * yearEnd))));
    }


    /**
     * Generates random UUID
     * @return String
     */
    public static String generateRandomUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * Generates random int in specific range
     * @param min - minimum value
     * @param max - maximum value
     * @return int
     */
    public static int generateRandomInt(int min, int max) {
        return min + new Random().nextInt((max - min) + 1);
    }

    /**
     * Generates random double in specific range
     * @param min - minimum value
     * @param max - maximum value
     * @return double
     */
    public static double generateRandomDouble(double min, double max, int roundedToDecPoints) {
        double d = min + (max - min) * new Random().nextDouble();
        String str = String.format("%1." + roundedToDecPoints + "f", d).replace(",", ".");
        return Double.parseDouble(str);
    }

    /**
     * Generates random boolean in specific range
     * @return double
     */
    public static boolean generateRandomBoolean() {
        return new Random().nextBoolean();
    }

    /**
     * Return random String from array
     * @param array - array of strings
     * return String
     */
    public static String getRandomItem(String[] array) { return array[new Random().nextInt(array.length)]; }

    /**
     * Return random string aktivny/neaktivny
     * return String
     */
    public static String getRandomStatus() {
        return new Random().nextBoolean() ? "aktívny" : "neaktívny";
    }

    /**
     * Return random string Ano/Nie
     * return String
     */
    public static String getRandomAnoNie() {
        return new Random().nextBoolean() ? "Nie" : "Áno";
    }

    /**
     * Return random number 1-9
     * return String
     */
    public static String generateRandomNumber(){
        return faker.regexify("[1-9]");
    }


}