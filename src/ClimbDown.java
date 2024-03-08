package src;

public class ClimbDown implements Operator{
      /*
     * Pre: Monkey and box are in the same room, and the monkey is at height HIGH
     * 
     * Post: Monkey is at height LOW
     * 
     * ClimbDown: monkey climbs down from the box
     */
    public ClimbDown() {

    }

    public boolean checkPreconditions(WorldState worldState) {
        // Check if the monkey and box are in the same room and if the monkey is at low height
        return worldState.isMonkeyAt(worldState.getRoomBoxIn()) && worldState.isMonkeyHeight(WorldState.HEIGHT_HIGH);
    }

    public WorldState applyPostconditions(WorldState worldState) {
        // Create and return a new WorldState with monkey at high height
        WorldState newWorldState = new WorldState(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn(), 
                                                  worldState.getRoomBananasIn(), worldState.getMonkeyHeight(),
                                                  worldState.isMonkeyHasBananas());
        newWorldState.setMonkeyHeight(WorldState.HEIGHT_HIGH);
        return newWorldState;
    }

    public String toString(){
        return "ClimbDown()";
    }

}
