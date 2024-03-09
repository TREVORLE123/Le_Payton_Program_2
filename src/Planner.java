package src;
import java.util.*;
public class Planner {
    private WorldState initialState;
    private WorldState goalState;

    public Planner(String monkeyLocation, String boxLocation, String bananaLocation){
        initialState = new WorldState(monkeyLocation, boxLocation, bananaLocation, initialState.HEIGHT_LOW, false);
        goalState = new WorldState(bananaLocation, bananaLocation, bananaLocation, initialState.HEIGHT_HIGH, true);
    }


    private int heuristic(WorldState state) {
        // Simple heuristic: Count the number of goals that are not satisfied
        int distanceToGoal = 0;
        if (!state.isMonkeyAt(state.getRoomBananasIn())) {
            distanceToGoal++;
        }
        if (!state.isMonkeyHeight(WorldState.HEIGHT_HIGH)) {
            distanceToGoal++;
        }
        // Add more sophisticated heuristics as needed
        return distanceToGoal;
    }

    public List<Operator> findPlan(){
        ArrayList <Operator> open = new ArrayList<Operator>();
        ArrayList <Operator> closed = new ArrayList<Operator>();

        open.add(new PlannerNode(initialState, null));

        
        return plan;
    }

}
