package MonteCarlo.ThreeDoorGame;

// This class runs the simulation for the Three Door Game.
public class RunGame {

    // Runs the simulation for a given number of games and a given switching strategy.
    // totalGames: number of times the game is played.
    // alwaysSwitch: true if the player always switches doors, false otherwise.
    // Returns the total number of wins.
    public int runSimulation(int totalGames, boolean alwaysSwitch) {
        // Create a DoorGame instance with the given strategy.
        DoorGame game = new DoorGame(alwaysSwitch);
        int wins = 0; // Counter for wins.

        // Play the game for totalGames times.
        for (int i = 0; i < totalGames; i++) {
            // If the player wins the game, increment the win counter.
            if (game.playGame()) {
                wins++;
            }
        }

        // Return the total number of wins.
        return wins;
    }

    // Calculates the win percentage based on wins and total games played.
    // wins: the number of games won.
    // totalGames: the total number of games played.
    // Returns the win percentage as a double.
    public double calculateWinPercentage(int wins, int totalGames) {
        // Divide wins by totalGames, convert to double, and multiply by 100.
        return ((double) wins / totalGames) * 100;
    }
}