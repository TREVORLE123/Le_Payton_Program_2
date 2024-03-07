package src;
public class Planner {
    private WorldState state = new WorldState();

    public void initalState(String monkey, String box, String banana){
        state.setInitialState(monkey, box, banana);
    }

    public String[] findPlan(){
        return null;
    }

}
