package src;
public class Move implements Operator{
    private String operatorName = "MOVE";
    private String moveFrom;
    private String moveTo;

    /*
     * Pre: Monkey is in from and the monkey is at height LOW 
     * 
     * Post: Monkey is in room to* and no longer in room from*
     * 
     * Move: monkey moves the box from room X to room Y
     */

    public Move(String from, String to){
        this.moveFrom = from;
        this.moveTo = to;
    }


    /*
     * 
     */
    public boolean checkPreconditions(WorldState worldState) {
        if(!worldState.isMonkeyAt(moveFrom)){
            return false;
        }

        if(!worldState.isMonkeyHeight(WorldState.HEIGHT_LOW)){
            return false;
        }

        return true;
    }

    /*
     * 
     */
    public WorldState applyPostconditions(WorldState worldState) {
        //create and return a new WorldState
        //with the monkey’s updated location
         // Create and return a new WorldState with updated locations for monkey and box
         WorldState newWorldState = new WorldState(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn(), 
                                                   worldState.getRoomBananasIn(), worldState.getMonkeyHeight(), 
                                                   worldState.isMonkeyHasBananas());
         newWorldState.setRoomMonkeyIn(to);
         newWorldState.setRoomBoxIn(to);
         return newWorldState;
    }

    @Override
    public String toString() {
        return "Move(" + moveFrom + "," + moveTo + ")";
    }
}
