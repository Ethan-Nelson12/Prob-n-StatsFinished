// Squirtle class
class Squirtle extends PokemonCard {
    public Squirtle() {
        super("Squirtle", 65, 10, 1);
    }
    
     // Special attack: Water Gun does 20 damage
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 20;
            System.out.println(name + " uses Water Gun for 20 damage!");
            System.out.println(opponent.getName() + "'s HP is now " + opponent.hp);
        } else {
            System.out.println(name + " does not have enough energy for Water Gun.");
        }
    }
}
