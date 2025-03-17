package PokemonDemo;


public class Pokemon {
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private String name;

    public Pokemon() {
    }

    public Pokemon(int hp, int attack, int defense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }
    public void attack(Pokemon p1, Pokemon p2){
        int attack = p1.attack - p2.defense;

        p2.setHp(p2.hp - attack);

    }
    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

   

    @Override
    public String toString() {
        return "{" +
            " hp='" + getHp() + "'" +
            ", attack='" + getAttack() + "'" +
            ", defense='" + getDefense() + "'" +
            ", speed='" + getSpeed() + "'" +
            "}";
    }
    
}
