package MonteCarlo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OddsofMulligan {
    public static int TOTAL_CARDS = 60;
    public static int HAND_SIZE = 7;
    public static int TRIALS = 10000;
    public static Random rand = new Random();

    public OddsofMulligan() {
    }


    // Simulate one game to see if it results in a mulligan
    private static boolean isMulligan(int numPokemon) {
        List<String> deck = new ArrayList<>();

        // Populate deck: numPokemon Pokémon and the rest Energy
        for (int i = 0; i < numPokemon; i++) {
            deck.add("Pokemon");
        }
        for (int i = numPokemon; i < TOTAL_CARDS; i++) {
            deck.add("Energy");
        }

        // Shuffle deck
        Collections.shuffle(deck, rand);

        // Draw HAND_SIZE cards
        int pokemonCount = 0;
        for (int i = 0; i < HAND_SIZE; i++) {
            if (deck.get(i).equals("Pokemon")) {
                pokemonCount++;
            }
        }

        // Mulligan if no Pokémon were drawn
        return pokemonCount == 0;
    }

    // Calculate the percentage chance of a mulligan for a given number of Pokémon
    public static double calculateMulliganPercentage(int numPokemon) {
        int mulliganCount = 0;

        // Run simulations
        for (int i = 0; i < TRIALS; i++) {
            if (isMulligan(numPokemon)) {
                mulliganCount++;
            }
        }

        // Calculate percentage
        return (mulliganCount / (double) TRIALS) * 100;
    }

    public static void main(String[] args) {
        System.out.println("Mulligan Chances in Pokémon TCG:");
        System.out.println("----------------------------------");

        // Run simulations for 1 to 60 Pokémon in the deck
        for (int numPokemon = 1; numPokemon <= TOTAL_CARDS; numPokemon++) {
            double mulliganChance = calculateMulliganPercentage(numPokemon);
            System.out.printf("%d Pokémon, %d Energy: %.2f%% Mulligan%n", numPokemon, TOTAL_CARDS - numPokemon, mulliganChance);
        }
    }
}
