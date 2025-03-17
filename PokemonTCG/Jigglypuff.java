// Jigglypuff class
class Jigglypuff extends PokemonCard {
    public Jigglypuff() {
        super("Jigglypuff", 80, 8, 1);
    }
    
     // Special attack: Sing does 10 damage and "puts the opponent to sleep"
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 10;
            System.out.println(name + " uses Sing for 10 damage and puts the opponent to sleep!");
            System.out.println(opponent.getName() + "'s HP is now " + opponent.hp);
        } else {
            System.out.println(name + " does not have enough energy for Sing.");
        }
    }
}
