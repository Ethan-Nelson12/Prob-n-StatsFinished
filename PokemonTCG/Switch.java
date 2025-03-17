import java.util.Scanner;

// Switch trainer card class
class Switch extends TrainerCard {
    public Switch() { 
        super("Switch"); 
    }
    
    // Switch effect: swap active Pokémon with one on the bench.
    @Override
    public void play(Player player, GameEngine engine) {
        if (!player.bench.isEmpty()) { // Check if there are Pokémon on the bench
            System.out.println(player.name + ", choose a bench Pokémon to switch with your active Pokémon:");
            for (int i = 0; i < player.bench.size(); i++) {
                System.out.println("[" + i + "] " + player.bench.get(i));
            }
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
             // Swap if a valid choice is made
            if (choice >= 0 && choice < player.bench.size()) {
                PokemonCard temp = player.activePokemon;
                player.activePokemon = player.bench.get(choice);
                player.bench.set(choice, temp);
                System.out.println(player.name + " switched active Pokémon to " + player.activePokemon.getName());
            } else {
                System.out.println("Invalid choice. Switch failed.");
            }
        } else {
            System.out.println("No Pokémon on the bench to switch with.");
        }
    }
}