// Meowth class
class Meowth extends PokemonCard {
    public Meowth() {
        super("Meowth", 70, 12, 1);
    }
    // Special attack: Pay Day does 20 damage
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 20; //Subtract hp from opponent
            System.out.println(name + " uses Pay Day for 20 damage!");
            System.out.println(opponent.getName() + "'s HP is now " + opponent.hp);
        } else {
            System.out.println(name + " does not have enough energy for Pay Day.");
        }
    }
}