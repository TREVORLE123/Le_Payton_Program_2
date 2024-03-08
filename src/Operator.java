package src;

public interface Operator {
    /**
     * Checks if the operator is applicable in the given world state.
     *
     * @param worldState The current world state.
     * @return True if the operator is applicable, false otherwise.
     */
    boolean checkPreconditions(WorldState worldState);

    /**
     * Applies the operator to the given world state, producing a new world state.
     *
     * @param worldState The current world state.
     * @return The new world state after applying the operator.
     */
    WorldState applyPostconditions(WorldState worldState);
}

