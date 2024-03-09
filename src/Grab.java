package src;

public class Grab implements Operator{
    private String operatorName = "GRAB";

    /*
     * Pre: Monkey and bananas must be in the same room, and the monkey must be HIGH
     * 
     * Post: Monkey has the bananas
     * 
     * Grab: monkey grabs the bananas at the current location
     * 
     */
    public Grab() {
        
    }

    public boolean checkPreconditions(WorldState worldState) {
        // Check if the monkey and bananas are in the same room and if the monkey is at high height
        return worldState.isMonkeyAt(worldState.getRoomBananasIn()) && worldState.isMonkeyHeight(WorldState.HEIGHT_HIGH);
    }

    public WorldState applyPostconditions(WorldState worldState) {
        // Create and return a new WorldState with monkey having bananas
        WorldState newWorldState = new WorldState(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn(), worldState.getRoomBananasIn(), worldState.getMonkeyHeight(), worldState.isMonkeyHasBananas());
        newWorldState.setMonkeyHasBananas(true);
        return newWorldState;
    }

    public String toString() {
        return "Grab()";
    }
    
}
