package src;
import java.util.ArrayList;
import java.util.List;

public class PlannerNode {
    private WorldState state;
    private List<Operator> plan;

    public PlannerNode(WorldState state, List<Operator> plan) {
        this.state = state;
        if (plan != null) {
            this.plan = new ArrayList<>(plan); // Ensure a deep copy for immutability
        } else {
            this.plan = new ArrayList<>();
        }
    }

    public WorldState getState() {
        return state;
    }

    public List<Operator> getPlan() {
        return plan;
    }

    // Generate a new PlannerNode by applying an operator to this node's state
    public PlannerNode applyOperator(Operator operator) {
        WorldState newState = operator.applyPostconditions(state); // Assume this method exists in Operator
        List<Operator> newPlan = new ArrayList<>(plan);
        newPlan.add(operator);
        return new PlannerNode(newState, newPlan);
    }
}
