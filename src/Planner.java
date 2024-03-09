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

            if (currentNode.getState().equals(goalState)) { // Assume isGoalState() checks if conditions for grabbing bananas are met
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

        return null; // No plan found
    }

    private List<PlannerNode> generateSuccessors(PlannerNode currentNode) {
        List<PlannerNode> successors = new ArrayList<>();
        List<Operator> operators = Arrays.asList(
            new Move(WorldState.ROOM_A, WorldState.ROOM_B), // Adjust as needed to reflect actual possible moves
            new Push(WorldState.ROOM_A, WorldState.ROOM_B), // This should be dynamic based on the state
            new ClimbUp(),
            new ClimbDown(),
            new Grab()
        );

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
