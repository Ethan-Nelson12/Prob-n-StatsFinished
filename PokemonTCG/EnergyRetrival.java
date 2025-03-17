import java.util.Iterator;

// EnergyRetrieval trainer card class
class EnergyRetrieval extends TrainerCard {
    public EnergyRetrieval() { 
        super("Energy Retrieval"); 
    }
    
    // This card retrieves up to 2 energy cards from the discard pile.
    @Override
    public void play(Player player, GameEngine engine) {
        int retrieved = 0;
         // Loop through discard pile to find energy cards
        Iterator<Card> it = engine.discardPile.iterator();
        while (it.hasNext() && retrieved < 2) {
            Card card = it.next();
            if (card instanceof EnergyCard) {
                player.hand.add(card); // Add the energy card to player's hand
                it.remove();  // Remove it from the discard pile
                retrieved++;
                System.out.println(player.name + " retrieved " + card.getName() + " into hand.");
            }
        }
        if (retrieved == 0) {
            System.out.println("No energy cards available in discard pile.");
        }
    }
}
