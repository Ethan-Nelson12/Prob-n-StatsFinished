import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// ---------------------
// GameEngine Class
// ---------------------
// This class controls the game flow, setting up players, decks, turns, etc.
class GameEngine {
    public List<Card> discardPile = new ArrayList<>(); // Global discard pile
    private Player player1;
    private Player player2;
    private Scanner sc = new Scanner(System.in);
    
    // Method to start the game and run the main game loop
    public void startGame() {
        // Ask players for their names
        System.out.print("Player 1, please enter your name: ");
        String name1 = sc.nextLine();
        System.out.print("Player 2, please enter your name: ");
        String name2 = sc.nextLine();
        
          // Create player objects
        player1 = new Player(name1);
        player2 = new Player(name2);
        
        // Build decks for both players
        player1.deck = buildRandomDeck();
        player2.deck = buildRandomDeck();
        
         // Shuffle the decks
        Collections.shuffle(player1.deck);
        Collections.shuffle(player2.deck);
        
        // Each player draws 7 cards as their starting hand
        for (int i = 0; i < 7; i++) {
            player1.drawCard();
            player2.drawCard();
        }
        
        // Checks for mulligan
        performMulligan(player1, player2);
        performMulligan(player2, player1);
        
        // Set prize piles
        for (int i = 0; i < 6; i++) {
            if (!player1.deck.isEmpty())
                player1.prizePile.add(player1.deck.remove(0));
            if (!player2.deck.isEmpty())
                player2.prizePile.add(player2.deck.remove(0));
        }
        
        // Let players choose their active Pokémon.
        chooseActivePokemon(player1);
        chooseActivePokemon(player2);
        
        // Coin flip to decide who goes first.
        System.out.println("\nFlipping a coin to decide who goes first...");
        Player current, opponent;
        if (new Random().nextBoolean()) {
            current = player1;
            opponent = player2;
        } else {
            current = player2;
            opponent = player1;
        }
        System.out.println(current.name + " wins the coin flip and will go first!\n");
        
        // Main game loop.
        while (true) {
            System.out.println("\n========== " + current.name + "'s Turn ==========");
            current.energyAttachedThisTurn = false; // Reset energy attachment flag at turn start
            current.drawCard(); // Draw a card at the beginning of the turn
            boolean turnOver = false;
            while (!turnOver) {
                // Display turn options
                System.out.println("\n" + current.name + ", choose an action:");
                System.out.println("[1] Play a card from hand");
                System.out.println("[2] Move a Pokémon to bench");
                System.out.println("[3] Attack");
                System.out.println("[4] End turn");
                int action = sc.nextInt(); // Consume newline
                sc.nextLine();
                switch (action) { // Handle player actions
                    case 1: // Play a card from hand
                        displayHand(current);
                        System.out.print("Select card index to play: ");
                        int idx = sc.nextInt();
                        sc.nextLine();
                        current.playCard(idx, this);
                        break;
                    case 2: // Move a Pokemon to bench
                        current.moveToBench();
                        break;
                    case 3: // Attack
                        // Check if there is an active Pokemon to attack with
                        if (current.activePokemon == null) {
                            System.out.println("No active Pokémon to attack with!");
                        } else {
                            System.out.println("Choose attack type: [1] Basic, [2] Special");
                            int type = sc.nextInt();
                            sc.nextLine();
                            if (type == 1) {
                                current.activePokemon.attack(opponent.activePokemon);
                            } else if (type == 2) {
                                current.activePokemon.specialAttack(opponent.activePokemon);
                            } else {
                                System.out.println("Invalid attack type.");
                            }
                            turnOver = true; //end the turn after attacking
                            
                            // Check if opponent's active Pokémon is knocked out
                            if (opponent.activePokemon.isKnockedOut()) {
                                System.out.println(opponent.activePokemon.getName() + " is knocked out!");
                                // Current player takes a prize card if available
                                if (!current.prizePile.isEmpty()) { //If there are no prizes left the current player wins
                                    current.prizePile.remove(0);
                                    System.out.println(current.name + " takes a Prize card! Remaining prizes: " + current.prizePile.size());
                                    if (current.prizePile.isEmpty()) {
                                        System.out.println(current.name + " wins the game!");
                                        return;
                                    }
                                }
                                // Opponent replaces active Pokémon from the bench if available
                                if (!opponent.bench.isEmpty()) {
                                    opponent.activePokemon = opponent.bench.remove(0);
                                    System.out.println(opponent.name + " sends out " + opponent.activePokemon.getName() + " as the new active Pokémon.");
                                } else {
                                    System.out.println(opponent.name + " has no Pokémon to replace the knocked out Pokémon. " + current.name + " wins!");
                                    return;
                                }
                            }
                        }
                        break;
                    case 4:
                        turnOver = true; // End turn option
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
            // Swap players
            Player temp = current;
            current = opponent;
            opponent = temp;
        }
    }
    
    // Method for the player to choose their active Pokemon from their hand
    private void chooseActivePokemon(Player player) {
        System.out.println("\n" + player.name + ", choose your active Pokémon from your hand:");
        List<Integer> validIndices = new ArrayList<>();
        // Loop through the hand and list indices of Pokemon cards
        for (int i = 0; i < player.hand.size(); i++) {
            if (player.hand.get(i) instanceof PokemonCard) {
                validIndices.add(i);
                System.out.println("[" + i + "] " + player.hand.get(i).getName());
            }
        }
        if (validIndices.isEmpty()) {
            System.out.println("No Pokémon cards available in hand for " + player.name + "!");
            return;
        }
        int choice = -1;
        while (!validIndices.contains(choice)) {
            System.out.print("Enter the index of the Pokémon to set as active: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            if (!validIndices.contains(choice)) {
                System.out.println("Invalid choice. Please select one of the displayed indices.");
            }
        }
         // Set the chosen Pokemon as active and remove it from the hand
        player.activePokemon = (PokemonCard) player.hand.remove(choice);
        System.out.println(player.name + " sets " + player.activePokemon.getName() + " as the active Pokémon.");
    }

    // Display the player's hand with card indices
    private void displayHand(Player player) {
        System.out.println(player.name + "'s Hand:");
        for (int i = 0; i < player.hand.size(); i++) {
            System.out.println("[" + i + "] " + player.hand.get(i).getName());
        }
    }
    
     // Check if the player has a Pokemon in hand if not, perform a mulligan.
    private void performMulligan(Player player, Player opponent) {
        while (!player.hasPokemonCardInHand()) {
            System.out.println("\n" + player.name + " has no Pokémon in hand and must take a mulligan!");
            // Put all hand cards back into the deck and shuffle
            player.deck.addAll(player.hand);
            player.hand.clear();
            Collections.shuffle(player.deck);
             // Draw a new hand of 7 cards
            for (int i = 0; i < 7; i++) {
                player.drawCard();
            }
            // Opponent receives an extra prize card for the mulligan
            if (!opponent.deck.isEmpty()) {
                opponent.prizePile.add(opponent.deck.remove(0));
                System.out.println(opponent.name + " receives an extra prize card due to mulligan!");
            }
        }
    }
    
    // Builds a deck with 20 Pokemon, 20 Trainer, and 20 Energy cards.
    private List<Card> buildRandomDeck() {
        List<Card> deck = new ArrayList<>();
        Random rand = new Random();
        // Add 20 Pokémon cards (randomly chosen)
        for (int i = 0; i < 20; i++) {
            int choice = rand.nextInt(6);
            switch (choice) {
                case 0: deck.add(new Bulbasaur()); break;
                case 1: deck.add(new Charmander()); break;
                case 2: deck.add(new Meowth()); break;
                case 3: deck.add(new Pikachu()); break;
                case 4: deck.add(new Squirtle()); break;
                case 5: deck.add(new Jigglypuff()); break;
            }
        }
        // Add 20 Trainer cards (randomly chosen)
        for (int i = 0; i < 20; i++) {
            int choice = rand.nextInt(4);
            switch (choice) {
                case 0: deck.add(new Switch()); break;
                case 1: deck.add(new Potion()); break;
                case 2: deck.add(new EnergyRetrieval()); break;
                case 3: deck.add(new ProfessorOak()); break;
            }
        }
        // Add 20 Energy cards (all basic energy for now)
        for (int i = 0; i < 20; i++) {
            deck.add(new EnergyCard("Basic"));
        }
        return deck;
    }
}