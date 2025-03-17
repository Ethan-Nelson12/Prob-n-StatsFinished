// Pikachu class
class Pikachu extends PokemonCard {
    public Pikachu() {
        super("Pikachu", 70, 10, 1);
    }
    // Special attack: Thunderbolt does 30 damage
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 30;
            System.out.println(name + " uses Thunderbolt for 30 damage!");
            System.out.println(opponent.getName() + "'s HP is now " + opponent.hp);
        } else {
            System.out.println(name + " does not have enough energy for Thunderbolt.");
        }
    }
}
