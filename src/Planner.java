package src;

import java.util.*;

public class Planner {
    //initialize current state, goal state, and storage lists for states and planner
    private WorldState initialState;
    private WorldState goalState;
    private PriorityQueue<PlannerNode> openList;
    private HashSet<WorldState> closedList;


    // Constructor: Initializes the Planner with specified initial locations for the monkey, box, and bananas.
    // Sets up the initial and goal states, as well as the priority queue (open list) and the set of explored states (closed list).
    public Planner(String monkeyLocation, String boxLocation, String bananaLocation){
        initialState = new WorldState(monkeyLocation, boxLocation, bananaLocation, WorldState.HEIGHT_LOW, false);
        goalState = new WorldState(bananaLocation, bananaLocation, bananaLocation, WorldState.HEIGHT_HIGH, true);

        // Comparator for the priority queue based on heuristic value
        Comparator<PlannerNode> comparator = Comparator.comparingInt(node -> heuristic(node.getState()));
        openList = new PriorityQueue<>(comparator);
        closedList = new HashSet<>();
    }

     // Heuristic function: Calculates a heuristic score for a given WorldState based on its closeness to the goal state.
    // The score increases for states further away from the goal, guiding the search towards the goal.
    private int heuristic(WorldState state) {
        // Heuristic: Prioritize states closer to achieving the goal conditions
        int score = 0;
        if (!state.getRoomMonkeyIn().equals(state.getRoomBananasIn())) score += 1;
        if (!state.getRoomBoxIn().equals(state.getRoomBananasIn())) score += 1;
        if (!state.isMonkeyHeight(WorldState.HEIGHT_HIGH)) score += 1;
        return score;
    }

    // Finds a plan to reach the goal state from the initial state using a heuristic-guided search.
    // Returns a list of operators that represents the actions to be taken to reach the goal state.
    // Returns an empty list if no plan is found.
    public List<Operator> findPlan() {
    openList.add(new PlannerNode(initialState, new ArrayList<>()));

    while (!openList.isEmpty()) {
        PlannerNode currentNode = openList.poll();

        if (currentNode.getState().isMonkeyHasBananas()){
            return currentNode.getPlan();
        }

        closedList.add(currentNode.getState());

        List<PlannerNode> successors = generateSuccessors(currentNode);

        for (PlannerNode successor : successors) {
            if (!closedList.contains(successor.getState())) {
                openList.add(successor);
            }
        }
    }

    // No plan found, return an empty list
    return Collections.emptyList();
}

    // Generates all possible successor states from a given node by applying all applicable operators.
    // Returns a list of PlannerNodes, each representing a possible state reachable from the current state along with the action that leads to it.
    private List<PlannerNode> generateSuccessors(PlannerNode currentNode) {
        List<PlannerNode> successors = new ArrayList<>();
        // Initialize the list of operators 
        List<Operator> operators = new ArrayList<>();
        List<String> options = Arrays.asList("A", "B", "C");
        List<Push> possiblePushes = new ArrayList<>();

        String monkey_room = currentNode.getState().getRoomMonkeyIn();
        String monkey_height = currentNode.getState().getMonkeyHeight();
        String banana_room = currentNode.getState().getRoomBananasIn();
        String box_room = currentNode.getState().getRoomBoxIn();
        //Checks if monkey is in the same room as box else move rooms
        if(!monkey_room.equals(box_room)){
            for (String option : options) {
                if (!option.equals(monkey_room)) {
                    operators.add(new Move(monkey_room, option));
                }
            }
        } 
        //if first check is true then we must check if monkey is in the same room as banana
        //and box else move box 
        else if(!box_room.equals(banana_room) && monkey_room.equals(box_room)){
            for (String option : options) {
                if (!option.equals(box_room)) {
                    possiblePushes.add(new Push(monkey_room, option));
                }
            }
        }
        //climbs up if monkey is not high
        else if(!monkey_height.equals("HIGH") && box_room.equals(banana_room) && monkey_room.equals(box_room)){
            operators.add(new ClimbUp());
        }
        //grabss if monkey does not have bananas and is high
        else if(monkey_height.equals("HIGH") && box_room.equals(banana_room) && monkey_room.equals(box_room)){
            operators.add(new Grab());
        }
       

    // Add all relevant push operators to the list of operators
        operators.addAll(possiblePushes);

        for (Operator operator : operators) {
            if (operator.checkPreconditions(currentNode.getState())) {
                WorldState newState = operator.applyPostconditions(currentNode.getState());
                List<Operator> newPlan = new ArrayList<>(currentNode.getPlan());
                newPlan.add(operator);
                successors.add(new PlannerNode(newState, newPlan));
            }
        }

        return successors;
    }
}
