package MonteCarlo.ThreeDoorGame;

// This class represents a door in the Three Door Game.
public class Door {

    // Indicates if this door has a car behind it.
    private final boolean hasCar;

    // Constructor: sets whether the door has a car.
    public Door(boolean hasCar) {
        this.hasCar = hasCar;
    }

    // Returns true if the door has a car, false otherwise.
    public boolean hasCar() {
        return hasCar;
    }
}