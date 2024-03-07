package src;
public class Move {
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
     * Pre: Monkey and box are in room X, and the monkey is at height LOW
     * 
     * Post: Monkey and box are in room Y, and no longer in room X
     * 
     * Push: monkey pushes the box from X to Y
     */
    public Push(String from, String to){

    }
    
    /*
     * Pre: Monkey and box are in the same room, and the monkey is at height LOW
     * 
     * Post: Monkey is at height HIGH
     * 
     * ClimbUp: monkey climbs up onto the box
     */
    public ClimbUp(){

    }

    /*
     * Pre: Monkey and box are in the same room, and the monkey is at height HIGH
     * 
     * Post: Monkey is at height LOW
     * 
     * ClimbDown: monkey climbs down from the box
     */
    public ClimbDown(){

    }

    /*
     * Pre: Monkey and bananas must be in the same room, and the monkey must be HIGH
     * 
     * Post: Monkey has the bananas
     * 
     * Grab: monkey grabs the bananas at the current location
     * 
     */
    public Grab(){

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
        …
    }

}
