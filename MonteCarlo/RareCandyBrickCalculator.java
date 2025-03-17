package MonteCarlo;
import java.util.ArrayList;
import java.util.Collections;

public class RareCandyBrickCalculator {
    
    private static final int PRIZE_CARDS = 6;
    private static final int SIMULATIONS = 10000;
    
    public double calculateBrickingProbability(int rareCandies) {
        int brickCount = 0;

        for (int i = 0; i < SIMULATIONS; i++) {
            if (simulateGame(rareCandies)) {
                brickCount++;
            }
        }

        return (double) brickCount / SIMULATIONS;
    }

    public static boolean simulateGame(int rareCandies) {
        ArrayList<String> deck = new ArrayList<>();

        // Add generic cards to deck (20 Pokémon, 20 Trainers, 20 Energy)
        for (int i = 0; i < 20; i++) deck.add("Pokemon");
        for (int i = 0; i < 20; i++) deck.add("Trainer");
        for (int i = 0; i < 20; i++) deck.add("Energy");

        // Add Rare Candies (replace Trainer cards)
        for (int i = 0; i < rareCandies; i++) deck.set(i, "Rare Candy");

        // Shuffle deck
        Collections.shuffle(deck);

        // Draw 6 Prize Cards
        int rareCandyCount = 0;
        for (int i = 0; i < PRIZE_CARDS; i++) {
            if (deck.get(i).equals("Rare Candy")) {
                rareCandyCount++;
            }
        }

        // Brick occurs if all Rare Candies are in the prize cards
        return rareCandyCount == rareCandies;
    }
}
