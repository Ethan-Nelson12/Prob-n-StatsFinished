// ---------------------
// EnergyCard Class
// ---------------------
// This class represents an Energy card.
class EnergyCard extends Card {
    private String energyType; // Type of energy (theres only basic in this game)
    
     // Constructor sets the energy type and creates a name like "Basic Energy"
    public EnergyCard(String energyType) {
        super(energyType + " Energy");
        this.energyType = energyType;
    }
    
     // Getter for energy type
    public String getEnergyType() {
        return energyType;
    }
}
