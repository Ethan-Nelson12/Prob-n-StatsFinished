package MonteCarlo.SameBirthday;

import java.util.HashSet;
import java.util.Set;

public class Run {
    
    // Number of people in a class
    private int classSize;
    // Number of simulation runs
    private int numRuns;

    // Constructor to set class size and number of runs
    public Run(int classSize, int numRuns) {
        this.classSize = classSize;
        this.numRuns = numRuns;
    }

    // Runs the simulation and returns the percentage of runs with a shared birthday
    public double run() {
        int matchCount = 0; // Count of runs with at least one shared birthday
        
        // Loop for the number of simulation runs
        for (int i = 0; i < numRuns; i++) {
            // If a shared birthday is found, increase the count
            if (hasSharedBirthday()) {
                matchCount++;
            }
        }

        // Calculate the percentage of runs with a shared birthday
        return (double) matchCount / numRuns * 100; 
    }

    // Checks if there is at least one shared birthday in a class
    private boolean hasSharedBirthday() {
        // Create a set to store unique birthdays
        Set<Integer> birthdays = new HashSet<>();
        
        // Loop through each person in the class
        for (int i = 0; i < classSize; i++) {
            Person person = new Person();
            // Try to add the birthday to the set; if it fails, a duplicate exists
            if (!birthdays.add(person.getBirthday())) {
                return true; // Shared birthday found
            }
        }
        return false; // No shared birthday found
    }
}