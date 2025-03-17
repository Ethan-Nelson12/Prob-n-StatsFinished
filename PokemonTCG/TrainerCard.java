// ---------------------
// TrainerCard Class
// ---------------------
// This abstract class represents Trainer cards. They have a special effect when played.
abstract class TrainerCard extends Card {
    public TrainerCard(String name) {
        super(name);
    }
     // Abstract method to play the trainer card, affecting the player or game engine.
    public abstract void play(Player player, GameEngine engine);
}