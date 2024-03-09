import java.util.ArrayList;
import java.util.List;

public class PlannerNode{
    private WorldState state;
    private List<Operator> plan;

    public PlannerNode(WorldState state, List<Operator> plan) {
            this.state = state;
            if(plan != null){
                this.plan = plan;
            }else{
                this.plan = new ArrayList<>();
            }
        }

        public WorldState getState() {
            return state;
        }

        public List<Operator> getPlan() {
            return plan;
        }
}