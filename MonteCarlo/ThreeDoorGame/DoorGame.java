package MonteCarlo.ThreeDoorGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DoorGame {

    // Indicates if the player always switches doors
    private final boolean alwaysSwitch;

    // Constructor sets the switching strategy
    public DoorGame(boolean alwaysSwitch) {
        this.alwaysSwitch = alwaysSwitch;
    }

    // Plays one round of the game and returns true if the player wins (gets the car)
    public boolean playGame() {
        Random rand = new Random(); // Create a random generator

        // Create a list of doors, one with a car and two with goats (false)
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(new Door(true));  // Door with a car
        doors.add(new Door(false)); // Door with a goat
        doors.add(new Door(false)); // Door with a goat
        
        // Randomly shuffle the doors
        Collections.shuffle(doors);

        // Player makes a random initial choice (0, 1, or 2)
        int playerChoice = rand.nextInt(3);

        // Host reveals a door that the player did not choose and that does not have a car
        int revealedDoor = (playerChoice + 1) % 3; // Default door to reveal
        if (doors.get(revealedDoor).hasCar()) {
            // If the default revealed door has the car, reveal the other door instead
            revealedDoor = (playerChoice + 2) % 3;
        }

        // Player's decision: if alwaysSwitch is true, they switch to the remaining door
        if (alwaysSwitch) {
            // Calculate the remaining door index (the one not chosen and not revealed)
            playerChoice = 3 - playerChoice - revealedDoor;
        }

        // Return true if the player's final choice has a car behind it
        return doors.get(playerChoice).hasCar();
    }
}