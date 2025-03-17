// Charmander class
class Charmander extends PokemonCard {
    public Charmander() {
        // Updated specialEnergyRequirement to 1.
        super("Charmander", 65, 15, 1);
    }
    // Special attack: Ember does 25 damage and discards 1 energy
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 25; //Subtract hp from opponent
            energyAttached--; // Use up 1 energy for Ember
            System.out.println(name + " uses Ember for 25 damage and discards 1 energy!");
        } else {
            System.out.println(name + " does not have enough energy for Ember.");
        }
    }
}
