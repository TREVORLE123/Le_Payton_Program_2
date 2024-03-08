package src;

public class Push implements Operator{
    private String operatorName = "PUSH";
    private String moveFrom;
    private String to;

    /*
     * Pre: Monkey and box are in room X, and the monkey is at height LOW
     * 
     * 
     * Post: Monkey and box are in room Y, and no longer in room X
     * 
     * Push: monkey pushes the box from X to Y
     */
    public Push(String from, String to){
        this.moveFrom = from;
        this.to = to; 
    }

    public boolean checkPreconditions(WorldState worldState){
        if(!worldState.isMonkeyAt(moveFrom) && !worldState.isBoxAt(moveFrom)){
            return false;
        }

        if(!worldState.isMonkeyHeight("low")){
            return false;
        }

        return true;
    }


    public WorldState applyPostconditions(WorldState worldState) {
        // Create and return a new WorldState with updated locations for monkey and box
        WorldState newWorldState = new WorldState(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn(), 
                                                  worldState.getRoomBananasIn(), worldState.getMonkeyHeight(), 
                                                  worldState.isMonkeyHasBananas());
        newWorldState.setRoomMonkeyIn(to);
        newWorldState.setRoomBoxIn(to);
        return newWorldState;
    }

    public String toString() {
        return "Push(" + moveFrom + "," + to + ")";

    }
}
