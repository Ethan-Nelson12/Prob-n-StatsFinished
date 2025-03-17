// ---------------------
// Card Class
// ---------------------
// This is the base class for all cards.
// Every card has a name.
abstract class Card {
    protected String name; // The card's name
    // Constructor to set the card's name
    public Card(String name) {
        this.name = name;
    }
    // Getter method to return the name of the card
    public String getName() { return name; }
}