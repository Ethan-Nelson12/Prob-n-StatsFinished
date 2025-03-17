// ProfessorOak trainer card class
class ProfessorOak extends TrainerCard {
    public ProfessorOak() { 
        super("Professor Oak"); 
    }
     // This card discards your hand and lets you draw 7 new cards.
    @Override
    public void play(Player player, GameEngine engine) {
        System.out.println(player.name + " uses Professor Oak to discard your hand and draw 7 new cards.");
        player.discardHand(engine);
        for (int i = 0; i < 7; i++) {
            player.drawCard();
        }
    }
}