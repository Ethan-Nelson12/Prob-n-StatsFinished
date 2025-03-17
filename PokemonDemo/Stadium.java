package PokemonDemo;

public class Stadium {
    
    public void battle(Pokemon p1, Pokemon p2){
        int p1Speed = p1.getSpeed();
        int p2Speed = p2.getSpeed();
        boolean flag = true;

        if(p1Speed > p2Speed){
            while(flag){
            p1.attack(p1, p2);
            if(p1.getHp() <= 0){
                System.out.println(p1.getName() + " wins!!!");
                flag = false;
            }
            p2.attack(p2, p1);
            if(p1.getHp() <= 0){
                System.out.println(p2.getName() + " wins!!!");
                flag = false;
            }
        }
        }
        else
        {
            while (flag) {
             p2.attack(p2, p1); 
            if(p1.getHp() <= 0){
                System.out.println(p2.getName() + " wins!!!");
                break;               
            }
            p1.attack(p1, p2);
            if(p2.getHp() <= 0){
                System.out.println(p1.getName() + " wins!!!");
                break;
            }
            }
        }
        

    }
}
