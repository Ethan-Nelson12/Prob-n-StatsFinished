import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

// ---------------------
// Player Class
// ---------------------
// This class holds data for a player (name, deck, hand, bench, active Pokémon, prize pile, etc.)
class Player {
    public String name; // Player's name
    public List<Card> deck = new ArrayList<>(); // Player's deck
    public List<Card> hand = new ArrayList<>(); // Player's hand
    public List<PokemonCard> bench = new ArrayList<>(); // Pokemon on bench
    public PokemonCard activePokemon; // Active Pokemon
    public List<Card> prizePile = new ArrayList<>(); // Prize pile of 6 cards
    public boolean energyAttachedThisTurn = false;  // Track if an energy was attached this turn
    
    // Constructor to set the player's name
    public Player(String name) {
        this.name = name;
    }
    
    // Method to draw a card from the deck
    public void drawCard() {
        if (deck.isEmpty()) {
            System.out.println(name + "'s deck is empty!");
            return;
        }
        Card drawn = deck.remove(0); // Remove card from top of deck
        hand.add(drawn); // Add card to hand
        System.out.println(name + " draws " + drawn.getName());
    }

    // Discard the whole hand into the discard pile
    public void discardHand(GameEngine engine) {
        engine.discardPile.addAll(hand);
        hand.clear();
    }
    
    // Check if the player's hand has at least one Pokemon card
    public boolean hasPokemonCardInHand() {
        for (Card card : hand) {
            if (card instanceof PokemonCard) {
                return true;
            }
        }
        return false;
    }
    
      // Move a Pokémon from hand to bench (if bench has less than 5 Pokémon)
    public void moveToBench() {
        if (bench.size() >= 5) {
            System.out.println("Bench is full!");
            return;
        }
        // Loop through hand to find a Pokemon card
        for (Iterator<Card> it = hand.iterator(); it.hasNext();) {
            Card card = it.next();
            if (card instanceof PokemonCard) {
                bench.add((PokemonCard) card); //Add to bench
                it.remove(); //Remove from hand
                System.out.println(name + " placed " + card.getName() + " on the bench.");
                return;
            }
        }
        System.out.println("No Pokémon card available in hand to place on the bench.");
    }
    
     // Play a card from the hand based on its index
    public void playCard(int index, GameEngine engine) {
        if (index < 0 || index >= hand.size()) {
            System.out.println("Invalid card index.");
            return;
        }
        Card card = hand.remove(index); // Remove the card from hand
        if (card instanceof TrainerCard) {
             // If it's a Trainer card, play its effect and add to discard pile
            ((TrainerCard) card).play(this, engine);
            engine.discardPile.add(card);
        } else if (card instanceof EnergyCard) {
            // Energy cards can only be attached once per turn
            if (!energyAttachedThisTurn && activePokemon != null) {
                activePokemon.attachEnergy();
                energyAttachedThisTurn = true;
                engine.discardPile.add(card);
            } else {
                System.out.println("Cannot attach more than one Energy per turn or no active Pokémon.");
                hand.add(card); // Put the card back into the hand
            }
        } else if (card instanceof PokemonCard) {
            // If it's a Pokémon card, set it as active if none is set,
             // otherwise try to put it on the bench
            if (activePokemon == null) {
                activePokemon = (PokemonCard) card;
                System.out.println(name + " sets " + activePokemon.getName() + " as the active Pokémon.");
            } else if (bench.size() < 5) {
                bench.add((PokemonCard) card);
                System.out.println(name + " places " + card.getName() + " on the bench.");
            } else {
                System.out.println("Bench is full. Cannot play Pokémon card.");
                hand.add(card); // Return the card to hand if no room
            }
        }
    }
}