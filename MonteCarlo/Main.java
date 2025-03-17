package MonteCarlo;

import MonteCarlo.SameBirthday.Run;
import MonteCarlo.ThreeDoorGame.RunGame;
import MonteCarlo.OddsofMulligan;
public class Main {
    public static void main(String[] args) {
        /* ----------------------SameBirthday--------------------------- */
        int classSize = 10; 
        int numRuns = 10000;
        Run simulation = new Run(classSize, numRuns);
        double probability = simulation.run();
        System.out.printf("With a class size of %d, the probability of a shared birthday is %.2f%%\n", classSize, probability);
        System.out.println();

        /* -----------------------ThreeDoorGame--------------------------- */
        RunGame game = new RunGame();
        int totalGames = 10000;
        boolean alwaysSwitch = true; 
        int wins = game.runSimulation(totalGames, alwaysSwitch);
        System.out.println("Win percentage: " + game.calculateWinPercentage(wins, totalGames) + "%");
        System.out.println();

        /* -------------------------OddsOfMulligan------------------------- */
        OddsofMulligan mully = new OddsofMulligan();
        System.out.println("Mulligan Chances in Pokémon TCG:");
        System.out.println("----------------------------------");

        for (int numPokemon = 1; numPokemon <= mully.TOTAL_CARDS; numPokemon++) {
            double mulliganChance = mully.calculateMulliganPercentage(numPokemon);
            System.out.printf("%d Pokémon, %d Energy: %.2f%% Mulligan%n", numPokemon, mully.TOTAL_CARDS - numPokemon, mulliganChance);
        }
        System.err.println();
        
        /* --------------------------------RareCandyBrickCalculator------------------------------------- */
        RareCandyBrickCalculator calculator = new RareCandyBrickCalculator();   
        for (int rareCandies = 1; rareCandies <= 4; rareCandies++) {
            double brickingProbability = calculator.calculateBrickingProbability(rareCandies);
            System.out.printf("With %d Rare Candies, probability of bricking: %.2f%%%n", rareCandies, brickingProbability * 100);
        }
    }
}
