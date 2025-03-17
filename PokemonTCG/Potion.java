// Potion trainer card class
class Potion extends TrainerCard {
    public Potion() { 
        super("Potion"); 
    }
    
     // Potion heals the active Pokémon by 20 HP.
    @Override
    public void play(Player player, GameEngine engine) {
        if (player.activePokemon != null) {
            player.activePokemon.heal(20);
        } else {
            System.out.println("No active Pokémon to heal.");
        }
    }
}