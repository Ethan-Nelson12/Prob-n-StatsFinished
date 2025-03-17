
// Bulbasaur class
class Bulbasaur extends PokemonCard {
    // Constructor sets Bulbasaur's stats
    public Bulbasaur() {
        // Updated specialEnergyRequirement to 1.
        super("Bulbasaur", 70, 12, 1); // Name, HP, basic damage, energy for special
    }

    // Special attack: Vine Whip does 20 damage if enough energy is attached
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 20; // Subtract 20 HP from opponent
            System.out.println(name + " uses Vine Whip for 20 damage!");
        } else {
            System.out.println(name + " does not have enough energy for Vine Whip.");
        }
    }
}