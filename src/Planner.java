package src;

import java.util.*;

public class Planner {
    private WorldState initialState;
    private WorldState goalState;
    private PriorityQueue<PlannerNode> openList;
    private HashSet<WorldState> closedList;

    public Planner(String monkeyLocation, String boxLocation, String bananaLocation){
        initialState = new WorldState(monkeyLocation, boxLocation, bananaLocation, WorldState.HEIGHT_LOW, false);
        goalState = new WorldState(bananaLocation, bananaLocation, bananaLocation, WorldState.HEIGHT_HIGH, true);

        // Comparator for the priority queue based on heuristic value
        Comparator<PlannerNode> comparator = Comparator.comparingInt(node -> heuristic(node.getState()));
        openList = new PriorityQueue<>(comparator);
        closedList = new HashSet<>();
    }

    private int heuristic(WorldState state) {
        // Heuristic: Prioritize states closer to achieving the goal conditions
        int score = 0;
        if (!state.getRoomMonkeyIn().equals(state.getRoomBananasIn())) score += 1;
        if (!state.getRoomBoxIn().equals(state.getRoomBananasIn())) score += 1;
        if (!state.isMonkeyHeight(WorldState.HEIGHT_HIGH)) score += 1;
        return score;
    }

    public List<Operator> findPlan() {
    openList.add(new PlannerNode(initialState, new ArrayList<>()));

    while (!openList.isEmpty()) {
        PlannerNode currentNode = openList.poll();

        if (currentNode.getState().equals(goalState)) {
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


    private List<PlannerNode> generateSuccessors(PlannerNode currentNode) {
        List<PlannerNode> successors = new ArrayList<>();
        // Initialize the list of operators 
    List<Operator> operators = new ArrayList<>();

    // Add relevant move operators based on the current state of the monkey and the box
    if (currentNode.getState().isMonkeyAt(WorldState.ROOM_A) && currentNode.getState().isBoxAt(WorldState.ROOM_A)) {
        operators.add(new Move(WorldState.ROOM_A, WorldState.ROOM_B));
    }
    if (currentNode.getState().isMonkeyAt(WorldState.ROOM_B) && currentNode.getState().isBoxAt(WorldState.ROOM_B)) {
        operators.add(new Move(WorldState.ROOM_B, WorldState.ROOM_A));
        operators.add(new Move(WorldState.ROOM_B, WorldState.ROOM_C));
    }
    if (currentNode.getState().isMonkeyAt(WorldState.ROOM_C) && currentNode.getState().isBoxAt(WorldState.ROOM_C)) {
        operators.add(new Move(WorldState.ROOM_C, WorldState.ROOM_B));
    }   

    // Add relevant push operators based on the current state of the monkey and the box
    List<Push> possiblePushes = new ArrayList<>();
    if (currentNode.getState().isMonkeyAt(WorldState.ROOM_A) && currentNode.getState().isMonkeyHeight(WorldState.HEIGHT_LOW) && currentNode.getState().isBoxAt(WorldState.ROOM_A)) {
        possiblePushes.add(new Push(WorldState.ROOM_A, WorldState.ROOM_B));
    }
    if (currentNode.getState().isMonkeyAt(WorldState.ROOM_B) && currentNode.getState().isMonkeyHeight(WorldState.HEIGHT_LOW) && currentNode.getState().isBoxAt(WorldState.ROOM_B)) {
        possiblePushes.add(new Push(WorldState.ROOM_B, WorldState.ROOM_A));
        possiblePushes.add(new Push(WorldState.ROOM_B, WorldState.ROOM_C));
    }
    if (currentNode.getState().isMonkeyAt(WorldState.ROOM_C) && currentNode.getState().isMonkeyHeight(WorldState.HEIGHT_LOW) && currentNode.getState().isBoxAt(WorldState.ROOM_C)) {
        possiblePushes.add(new Push(WorldState.ROOM_C, WorldState.ROOM_B));
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
