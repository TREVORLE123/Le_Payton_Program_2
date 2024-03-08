public class ClimbUp implements Operator {
    /*
     * Pre: Monkey and box are in the same room, and the monkey is at height LOW
     * 
     * Post: Monkey is at height HIGH
     * 
     * ClimbUp: monkey climbs up onto the box
     */
    public ClimbUp() {

    }

    public boolean checkPreconditions(WorldState worldState) {
        // Check if the monkey and box are in the same room and if the monkey is at low height
        return worldState.isMonkeyAt(worldState.getRoomBoxIn()) && worldState.isMonkeyHeight(WorldState.HEIGHT_LOW);
    }

    public WorldState applyPostconditions(WorldState worldState) {
        // Create and return a new WorldState with monkey at high height
        WorldState newWorldState = new WorldState(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn(), worldState.getRoomBananasIn(), worldState.getMonkeyHeight(), worldState.isMonkeyHasBananas());
        newWorldState.setMonkeyHeight(WorldState.HEIGHT_HIGH);
        return newWorldState;
    }

    public String toString(){
        return "ClimbUp()";
    }
}
