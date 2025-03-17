// ---------------------
// PokemonCard Class
// ---------------------
// This abstract class extends Card and represents a Pokemon card.
// It holds basic info like HP, basic attack damage, attached energy, etc.
abstract class PokemonCard extends Card {
    protected int maxHp; // Maximum health points
    protected int hp; // Current health points
    protected int basicAttackDamage; // Damage done by a basic attack
    protected int energyAttached = 0; // Number of energies attached
    protected int specialEnergyRequirement; // Energies needed for special attack 

    // Constructor to set up a Pokémon card with its stats
    public PokemonCard(String name, int hp, int basicAttackDamage, int specialEnergyRequirement) {
        super(name);   // Call the base class constructor
        this.maxHp = hp; // Set maximum HP
        this.hp = hp; // Start with full health
        this.basicAttackDamage = basicAttackDamage; // Set basic attack damage
        this.specialEnergyRequirement = specialEnergyRequirement;  // Set energy needed for special attack
    }

    // Method to attach one energy card to this Pokémon
    public void attachEnergy() {
        energyAttached++; // Increase energy count by 1
        System.out.println(name + " attached an energy. Total energy: " + energyAttached);
    }
    // Method for a basic attack on an opponent's Pokémon
    public void attack(PokemonCard opponent) {
        if (energyAttached > 0) {  // Check if this Pokémon has any energy
            opponent.hp -= basicAttackDamage; // Subtract basic attack damage from opponent's HP
            System.out.println(name + " attacks " + opponent.getName() + " for " + basicAttackDamage + " damage!");
            System.out.println(opponent.getName() + "'s HP is now " + opponent.hp);
        } else {
            System.out.println(name + " does not have any energy to attack.");
        }
    }
    
    // Abstract method for a special attack. Each Pokémon must implement this.
    public abstract void specialAttack(PokemonCard opponent);
    
     // Check if this Pokémon is knocked out (HP 0 or less)
    public boolean isKnockedOut() {
        return hp <= 0;
    }
    
     // Method to heal this Pokémon by a given amount (for potion)
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
        System.out.println(name + " heals for " + amount + " HP. Current HP: " + hp);
    }
    
     // Override toString method to show card info
    @Override
    public String toString() {
        return name + " (HP: " + hp + "/" + maxHp + ", Energy: " + energyAttached + ")";
    }
}

class Bulbasaur extends PokemonCard {
    public Bulbasaur() {
        super("Bulbasaur", 70, 12, 1);
    }
    
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 20;
            System.out.println(name + " uses Vine Whip for 20 damage!");
            System.out.println(opponent.getName() + "'s HP is now " + opponent.hp);
        } else {
            System.out.println(name + " does not have enough energy for Vine Whip.");
        }
    }
}

class Charmander extends PokemonCard {
    public Charmander() {
        super("Charmander", 60, 15, 1);
    }
    
    @Override
    public void specialAttack(PokemonCard opponent) {
        if (energyAttached >= specialEnergyRequirement) {
            opponent.hp -= 25;
            energyAttached--; // Ember costs one energy to use
            System.out.println(name + " uses Ember for 25 damage and discards 1 energy!");
            System.out.println(opponent.getName() + "'s HP is now " + opponent.hp);
        } else {
            System.out.println(name + " does not have enough energy for Ember.");
        }
    }
}