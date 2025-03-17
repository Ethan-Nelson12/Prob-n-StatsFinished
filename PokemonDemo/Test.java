package PokemonDemo;

public class Test{
    public static void main(String[] args) {
        Stadium stadium = new Stadium();
        Charmander charr = new Charmander();
        charr.setHp(80);
        charr.setAttack(35);
        charr.setSpeed(50);
        charr.setDefense(20);
        charr.setName("Charmander");
        Pikachu pik = new Pikachu();
        pik.setAttack(30);
        pik.setDefense(25);
        pik.setHp(100);
        pik.setSpeed(40);
        pik.setName("Pikachu");

        stadium.battle(pik, charr);

        System.out.println("Mulligan Chances in Pokémon TCG:");
        System.out.println("----------------------------------");

    }
}
