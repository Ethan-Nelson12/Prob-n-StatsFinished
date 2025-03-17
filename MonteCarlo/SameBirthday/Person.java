package MonteCarlo.SameBirthday;

import java.util.Random;

public class Person {
    // Store the person's birthday as an integer (1 to 365)
    int birthday;

    // Constructor that assigns a random birthday to the person
    public Person() {
        Random rand = new Random(); // Create a new Random object
        birthday = rand.nextInt(365) + 1; // Generate a random number between 0 and 364, then add 1 to get a value from 1 to 365
    }

    // Method to retrieve the person's birthday
    public int getBirthday() {
        return birthday; // Return the birthday value
    }
}